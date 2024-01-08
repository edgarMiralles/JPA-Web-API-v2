/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 *
 * @author edgar
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "GameType.findIn",
        query = "SELECT g FROM GameType g WHERE g.id IN :ids"),
    @NamedQuery(name = "GameType.findAll",
                query = "SELECT g FROM GameType g"),
    @NamedQuery(name = "GameType.findById",
        query = "SELECT g FROM GameType g WHERE g.id = :id")
})

public class GameType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy="types", cascade = CascadeType.PERSIST)
    @JsonbTransient
    private Collection<Game> games;

    public GameType(){
        games = new ArrayList<>();
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
        return "GameType{" + "id=" + id + ", name=" + name + '}';
    }
 
    
}
