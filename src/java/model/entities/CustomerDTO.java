/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Author:  jordi
 * Created: 01 dec 2023
 */

public class CustomerDTO implements Serializable{
private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private Collection<Rental> rentals;
    
    public CustomerDTO(){
        rentals = new ArrayList<Rental>();
    }
    
    public CustomerDTO(Customer customer){
        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
        rentals = customer.getRentals();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Collection<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public String toString() {
        return "Customer[ name=" + name + " ]";
    }
    
}
