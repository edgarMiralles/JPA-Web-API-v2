/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;


/**
 *
 * @author edgar
 */
@NamedQueries({
    @NamedQuery(name = "Rental.findById",
                query = "SELECT r FROM Rental r WHERE r.id = :id")
})
@Entity
public class Rental implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private float price;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "FINALDATE")
    private Date finalDate;
    
    @Transient
    private int gameId;
    @Transient
    private int customerId;
    
    @ManyToOne
    private Game rentedGame;
    
    @ManyToOne
    private Customer tenant;


        
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int id) {
        this.customerId = id;
    }
    
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int id) {
        this.gameId = id;
    }
    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getFinalDate() {
        return finalDate;
    }
    
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Game getRentedGame() {
        return rentedGame;
    }

    public void setRentedGame(Game rentedGame) {
        this.rentedGame = rentedGame;
    }

    public Customer getTenant() {
        return tenant;
    }

    public void setTenant(Customer tenant) {
        this.tenant = tenant;
    }
    
    @Override
    public String toString() {
        return "Rental[ id=" + id + " ]";
    }
    
}
