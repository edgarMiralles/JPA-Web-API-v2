package service;

import authn.Secured;
import model.entities.Console;
import model.entities.Game;
import model.entities.GameType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("jueguito")
public class GameService {
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;    
    
    public GameService(EntityManager em) {
        this.em = em;
    }
    
    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createGame(Game game) {
        
        if(gameExists(game.getName(), game.getConsole().getId())){
            //409
            return Response.status(Response.Status.CONFLICT).entity("Game already exists").build();
        }
        /*return Response.status(Response.Status.ACCEPTED).entity("Hola").build();
        game.setName(name);
        game.setDescription(description);
        
        Console console = em.find(Console.class, consoleId);
        game.setConsole(console);
        
        Query findIn = em.createNamedQuery("GameType.findIn");
        List<GameType> typesList = findIn.setParameter("ids", typeIds).getResultList();
        game.setTypes(typesList);
        for(GameType type : typesList){
            type.getGames().add(game);
            em.persist(type);
        }
        
        em.persist(game);*/
        return Response.ok(game).build();
    }
    
    private boolean gameExists(String name, int consoleId){
        Query findByDetails = em.createNamedQuery("Game.findByDetails");
        findByDetails.setParameter("name", name);
        findByDetails.setParameter("consoleId", consoleId);
        
        List<Game> games = findByDetails.getResultList();
        
        return !games.isEmpty();
    }
}
