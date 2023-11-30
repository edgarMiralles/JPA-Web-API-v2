/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.entities.Customer;
import model.entities.Game;
import model.entities.Rental;
import model.entities.RentalDTO;
/**
 *
 * @author URV
 */
public class RentalService {
    protected EntityManager em;
    
    public RentalService(EntityManager em) {
        this.em = em;
    }
    
    public RentalDTO CreateRental(float price, int idGame, int idCustomer, int weeks) {
        
        //Mirar si esta autenticado el usuario con su id
                
        Rental rental = new Rental();
        rental.setPrice(price);
        
        Game game = em.find(Game.class, idGame);
        rental.setRentedGame(game);
        
        Customer customer = em.find(Customer.class, idCustomer);

                
        rental.setTenant(customer);
        
        


        // Devolver el id de la renta, la fecha de devolucion, y el precio
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rental.getId()); // ID de la renta
        rentalDTO.setPrice(rental.getPrice()); // Precio
        
        Calendar calendar = Calendar.getInstance();
        Date startDate = calendar.getTime();
        calendar.add(Calendar.WEEK_OF_YEAR, weeks);
        Date finalDate = calendar.getTime();
        
        rentalDTO.setFinalDate(finalDate); // Fecha de retorno (puedes ajustarla según tus necesidades)
        
        rental.setStartDate(startDate);

        rental.setFinalDate(finalDate);

        
        Collection<Rental> actualRentals = customer.getRentals();
        actualRentals.add(rental);
        customer.setRentals(actualRentals);
        
        em.persist(customer);
        em.persist(rental);

        return rentalDTO;
    }
    
    public Rental ExistRental(int idRental){
        //Mirar si esta autenticado el usuario con su id

        Query findRentalId = em.createNamedQuery("Rental.findById"); 
        findRentalId.setParameter("id",idRental);
        
        Rental rentalFound = (Rental) findRentalId.getSingleResult();
        
        return rentalFound;
    }

    
    
}
