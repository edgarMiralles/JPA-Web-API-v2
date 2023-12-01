/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.CascadeType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Transient;

/**
 *
 * @author edgar
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Game.findById",
                query = "SELECT g FROM Game g WHERE g.id = :id"),
    @NamedQuery(name = "Game.findByDetails",
                query = "SELECT g FROM Game g WHERE g.name = :name AND g.console.id = :consoleId"),
    @NamedQuery(name= "Game.findFromTypesAndConsole",
                query = "SELECT g FROM Game g  WHERE g.console = :console AND g.types = :types"),
    @NamedQuery(name= "Game.findFromConsole",
                query = "SELECT g FROM Game g  WHERE g.console = :console"),
    @NamedQuery(name= "Game.findFromTypes",
                query = "SELECT g FROM Game g  WHERE g.types = :types"),
    @NamedQuery(name= "Game.findAll",
                query = "SELECT g FROM Game g")
})
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int stock;
    private String description;
    
    @Embedded 
    private Address address;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Console console;
    @Transient
    private int consoleId; 
    
    @ManyToMany
    private Collection<GameType> types;

    public Game(){
        types = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Collection<GameType> getTypes() {
        return types;
    }

    public void setTypes(Collection<GameType> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Game[ id=" + id + " ]";
    }
    
}
