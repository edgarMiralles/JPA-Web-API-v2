/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;


/**
 *
 * @author jotdi
 */

@NamedQueries({
    @NamedQuery(name = "Rental.findById",
                query = "SELECT r FROM Rental r WHERE r.id = :id")
})
@Entity
public class Rental implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
    @SequenceGenerator(name = "rental_seq", sequenceName = "RENTAL_SEQ", allocationSize = 1)
    private String id;
    private float price;
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FINALDATE")
    private Date finalDate;
    
    @Transient
    private Collection<Long> gameId;
    @Transient
    private Long customerId;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Game> rentedGames;
    
    @ManyToOne
    @JoinColumn(name = "TENANT_ID")
    @JsonbTransient
    private Customer tenant;
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long id) {
        this.customerId = id;
    }

    public Collection<Long> getGameId() {
        return gameId;
    }

    public void setGameId(Collection<Long> gameId) {
        this.gameId = gameId;
    }

    public Collection<Game> getRentedGames() {
        return rentedGames;
    }

    public void setRentedGames(Collection<Game> rentedGames) {
        this.rentedGames = rentedGames;
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
