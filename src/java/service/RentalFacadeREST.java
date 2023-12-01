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
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import model.entities.Customer;
import model.entities.Game;
import model.entities.Rental;
import model.entities.RentalDTO;
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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Rental entity, @Context UriInfo uriInfo) {
        

        try {
            Collection<Game> rentedGames = new ArrayList<>(); // Inicializa la colección
            Query query = em.createNamedQuery("Game.findById", Game.class);
            for(int idGame:entity.getGameId()){
                query.setParameter("id", idGame);
                rentedGames.add((Game) query.getSingleResult());
            }
            entity.setGames(rentedGames);
        } catch (NoResultException e) {
            // Manejar el caso donde no se encuentra el juego
            return Response.status(Response.Status.NOT_FOUND).entity("Game not found").build();
        }

        try {
            Query query = em.createNamedQuery("Customer.findById", Customer.class);
            query.setParameter("id", entity.getCustomerId());
            Customer tenant = (Customer) query.getSingleResult();
            entity.setTenant(tenant);
        } catch (NoResultException e) {
            // Manejar el caso donde no se encuentra el cliente
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        }
        
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(entity.getId()); // ID de la renta
        rentalDTO.setPrice(entity.getPrice()); // Precio
        rentalDTO.setFinalDate(entity.getFinalDate()); // Fecha de retorno (puedes ajustarla según tus necesidades)
        
        entity.getTenant().getRentals().add(entity);
        super.create(entity);

        // Construir la URI de la entidad creada
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(entity.getId())); // Asumiendo que getId() devuelve la ID de la entidad

        return Response.created(uriBuilder.build()).entity(rentalDTO).build();
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
    public Response find(@PathParam("id") int id) {
        Rental rental = super.find(id);

        if (rental != null) {
            return Response.ok().entity(rental).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Rental not found for id: " + id).build();
        }
    }


    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rental> findAll() {
        return super.findAll();
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
