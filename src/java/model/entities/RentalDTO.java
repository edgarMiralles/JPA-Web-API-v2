/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Author:  jordi
 * Created: 01 dec 2023
 */

public class RentalDTO implements Serializable{
    private String id;
    private float price;
    private Date finalDate;
    
    public RentalDTO(){
    }
    
    public RentalDTO(String id, float price, Date finalDate){
        this.id = id;
        this.price = price;
        this.finalDate = finalDate;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public Date getFinalDate() {
        return finalDate;
    }
    
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
