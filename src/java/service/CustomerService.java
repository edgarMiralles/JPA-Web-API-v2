/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.entities.Customer;
import model.entities.Rental;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 *
 * @author Usuario
 */
public class CustomerService {
    protected EntityManager em;
    
    public CustomerService(EntityManager em) {
        this.em = em;
    }
    
    public List<Customer> getCustomers(){
        Query query = em.createNamedQuery("Customer.findCustomers", Customer.class);
        List<Customer> customers = query.getResultList();
        
        // Eliminar información confidencial
        for (Customer customer : customers) {
            customer.setPassword(null);
        }
       
        return customers;
    }
            
    public Customer getCustomer(int idCostumer){
        Query findCustomerId = em.createNamedQuery("Customer.findById"); 
        findCustomerId.setParameter("id",idCostumer);
        
        Customer customerFound = (Customer) findCustomerId.getSingleResult();
        
        return customerFound;
    }
    
    public void setCustomer(int idCostumer, Customer newCustomer){
        
        Customer existingCustomer = em.find(Customer.class, idCostumer);
        if (existingCustomer != null) {

            existingCustomer.setName(newCustomer.getName());
            existingCustomer.setEmail(newCustomer.getEmail());
            existingCustomer.setPassword(newCustomer.getPassword());
            existingCustomer.setId(newCustomer.getId());
            existingCustomer.setRentals(newCustomer.getRentals());

            em.persist(existingCustomer);
        }
    }
}
