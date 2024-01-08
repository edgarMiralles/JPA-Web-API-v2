/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.CascadeType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
/**
 *
 * @author edgar
 */
@NamedQueries({
    @NamedQuery(name = "Console.findById",
                query = "SELECT c FROM Console c WHERE c.id = :id"),
    @NamedQuery(name = "Console.findAll",
                query = "SELECT c FROM Console c"),
    @NamedQuery(name = "Console.findIn",
                query = "SELECT c FROM Console c WHERE c.id IN :ids")
})


@Entity
public class Console implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "console",cascade = CascadeType.PERSIST)
    private Collection<Game> games;
    
    public Console(){
        games = new ArrayList<Game>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Game> getGames() {
        return games;
    }

    public void setGames(Collection<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Game[ id=" + id + " ]";
    }
}
