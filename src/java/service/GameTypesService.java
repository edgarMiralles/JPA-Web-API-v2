package service;

import model.entities.GameType;
import jakarta.persistence.EntityManager;

public class GameTypesService {
    protected EntityManager em;
    
    public GameTypesService(EntityManager em) {
        this.em = em;
    }

    public void seedGameTypes() {
        GameType gameType = new GameType();
        gameType.setName("Action");
        em.persist(gameType);
        
        gameType = new GameType();
        gameType.setName("Platforms");
        em.persist(gameType);
        
        gameType = new GameType();
        gameType.setName("FPS");
        em.persist(gameType);
        
        gameType = new GameType();
        gameType.setName("3D");
        em.persist(gameType);
        
        gameType = new GameType();
        gameType.setName("2D");
        em.persist(gameType);
        
        gameType = new GameType();
        gameType.setName("Racing");
        em.persist(gameType);
    }
}
