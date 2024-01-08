/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import model.entities.Customer;
import model.entities.Game;
import model.entities.Rental;

/**
 *
 * @author edgar
 */
public class PostRentalParams {
    private Long customerId;
    private Collection<Long> gameIds;
    private Rental rental;
    
    public PostRentalParams(Rental rental){
        this.customerId = rental.getCustomerId();
        this.gameIds = rental.getGameId();
        this.rental = rental;
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Collection<Long> getGameIds() {
        return gameIds;
    }

    public void setGameIds(Collection<Long> gameIds) {
        this.gameIds = gameIds;
    }
    
    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
    
    public Response handleValidationErrors(EntityManager em) {
        
        int customerValidated = validateCustomer(em);
        if (customerValidated == -1) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("No Customer provided for the Rental").build();        
        } else if (customerValidated == -2) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("ID provided customer doesn't exist").build();
        }
        int gameIdsValidated = validateGameIds(em);
        switch (gameIdsValidated) {
            case -1:
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("No Games provided for the Rental").build();
            case -2:
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Game id must be an positive integer value").build();
            case -3:
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("ID provided game doesn't exist").build();
            case -4:
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("A game doesn't exist").build();
            default:
                break;
        }
          
        Response gamesValidated = validatedGames(em);
        if (gamesValidated != null){
            return gamesValidated;
        }
        
        int rentalValidated = validatedRental();
        if (rentalValidated == -1){
            return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Price must be a valid value").build();
        }
        
        return null;
    }

    private int validateCustomer(EntityManager em) {
        if (this.customerId==null || this.customerId==0) {
            return -1;            
        }
        if (em.find(Customer.class, this.customerId)==null)
            return -2;
        return 0;
    }
    
    private int validateGameIds(EntityManager em) {
        if(this.gameIds == null){
            return -1;
        }
        
        for (Long id : this.gameIds) {
            if (id <= 0) {
                return -2;
            }if(id == null){
                return -3;
            }if(em.find(Game.class,id) == null){
                return -4;
            }
        }  
        return 0;
    }
    
    private Response validatedGames(EntityManager em){
        List<Game> games = em.createNamedQuery("Game.findIn", Game.class)
                .setParameter("ids",this.gameIds)
                .getResultList();
        
        for(Game game : games){                
            if(game == null){
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Game must be exists").build();
            }if (game.getStock() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Stock of game '" + game.getName() + "' is depleted.").build();
            }
        }
        return null;
    }
    
    private int validatedRental(){
        if(Float.toString(this.rental.getPrice()).isEmpty() || Float.toString(this.rental.getPrice())==null){
            return -1;
        }
        return 0;
    }
}
