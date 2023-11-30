package service;

import model.entities.Console;
import model.entities.Game;
import model.entities.GameType;
import java.util.Collection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ConsoleService {
    protected EntityManager em;
    
    public ConsoleService(EntityManager em) {
        this.em = em;
    }

    public void seedConsoles() {
        Console console = new Console();
        console.setName("MayDay Station");
        em.persist(console);
        
        console = new Console();
        console.setName("Ecs Bocs");
        em.persist(console);
        
        console = new Console();
        console.setName("Nantero Swap");
        em.persist(console);
        
        console = new Console();
        console.setName("PC");
        em.persist(console);
    }
}
