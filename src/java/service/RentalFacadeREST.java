package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import authn.Secured;
import jakarta.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import model.entities.Customer;
import model.entities.Game;
import model.entities.Rental;
import model.entities.RentalDTO;
import validation.PostRentalParams;
/**
 * Author:  jordi
 * Created: 01 dec 2023
 */
@Stateless
@Path("rental")
public class RentalFacadeREST extends AbstractFacade<Rental> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public RentalFacadeREST() {
        super(Rental.class);
    }

    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Rental rental, @Context UriInfo uriInfo) {
 
        if (rental == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("The JSON payload is null.").build();
        }
        
        PostRentalParams rentalValidator = new PostRentalParams(rental);
        Response validationResponse = rentalValidator.handleValidationErrors(em);
        if (validationResponse != null) {
            return validationResponse;
        }

        Collection<Long> gameIds = rental.getGameId();

        List<Game> games = em.createNamedQuery("Game.findIn", Game.class)
                .setParameter("ids", gameIds)
                .getResultList();

        float priceTotal = 0;
        for (Game game : games) {
            int occurrences = Collections.frequency(gameIds, game.getId());
            int newRentedCount = game.getRentedCount() + occurrences;
            game.setRentedCount(newRentedCount);
            game.setStock(game.getStock() - occurrences);
            priceTotal += game.getPrice() * occurrences;
        }

        rental.setRentedGames(games);
        rental.setPrice(priceTotal);
        
        Customer tenant = em.createNamedQuery("Customer.findById",Customer.class)
                .setParameter("id", rental.getCustomerId())
                .getSingleResult();
        rental.setTenant(tenant);
        rental.setCustomerId(tenant.getId());
        tenant.getRentals().add(rental);
        
        
        Long customerId = rental.getCustomerId();

        String rentalId = String.valueOf(customerId)+"_"+String.valueOf(em.find(Customer.class, customerId).getRentals().size());
        rental.setId(rentalId);

        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rental.getId()); 
        rentalDTO.setPrice(priceTotal); 
        
        Date finalDate = addOneWeek(rental.getStartDate());
        rentalDTO.setFinalDate(finalDate); 
        rental.setFinalDate(finalDate);
        
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(rental.getId()); 

        super.create(rental);   
        return Response.created(uriBuilder.build()).entity(rentalDTO).build();
    }
    
    private Date addOneWeek(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        calendar.add(Calendar.DAY_OF_YEAR, 7);
        return calendar.getTime();
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Rental entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Secured
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") String id) {
        
        Rental rental = super.find(id);
        
        if (rental != null) {
            return Response.ok().entity(rental).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Rental not found for id: " + id).build();
        }
    }


    @GET
    @Secured
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rental> findAll(@QueryParam("userId") Long idUser) {
                
        List<Rental> rentalsReturn = new ArrayList<Rental>();
        List<Rental> rentals = super.findAll();
        for(Rental rental : rentals){
            if(rental.getTenant().getId()==idUser){
                rentalsReturn.add(rental);
            }
        }
        return rentalsReturn;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rental> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
