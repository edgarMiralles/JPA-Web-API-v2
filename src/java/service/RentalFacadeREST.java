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
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
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
    @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Rental entity, @Context UriInfo uriInfo) {
               
        try {
            if (entity == null) {
                // Si el JSON es nulo, retornar un error
                return Response.status(Response.Status.BAD_REQUEST).entity("The JSON payload is null.").build();
            }

            List<Integer> gameIdList = Arrays.stream(entity.getGameId()).boxed().collect(Collectors.toList());

            List<Game> games = em.createNamedQuery("Game.findIn", Game.class)
                    .setParameter("ids", gameIdList)
                    .getResultList();
            
            for(Game game : games){
                // Obtener el stock actual
                int currentStock = game.getStock();

                // Reducir el stock en 1
                game.setStock(currentStock - 1);

                // Verificar si el stock es cero
                if (game.getStock() <= 0) {
                    // Stock agotado, lanzar respuesta erronea
                    return Response.status(Response.Status.BAD_REQUEST).entity("Stock of game '" + game.getName() + "' is depleted.").build();
                }
            }
            
            entity.setGames(games);
            
        } catch (NoResultException e) {
            // Manejar el caso donde no se encuentra el juego
            return Response.status(Response.Status.NOT_FOUND).entity("Game not found").build();
        }

        try {        
            Customer tenant = em.find(Customer.class,entity.getCustomerId());
            tenant.getRentals().add(entity);
            entity.setTenant(tenant);
        } catch (Exception e) {
            // Manejar el caso donde no se encuentra el cliente
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        }
        
        try{                    
            String customerId = entity.getCustomerId();

            // Concatenar el ID del cliente y el número de rentas
            String rentalId = String.valueOf(customerId)+"_"+String.valueOf(em.find(Customer.class, customerId).getRentals().size());
            entity.setId(rentalId); // Establecer la ID generada

            RentalDTO rentalDTO = new RentalDTO();
            rentalDTO.setId(entity.getId()); // ID de la renta
            rentalDTO.setPrice(entity.getPrice()); // Precio
            rentalDTO.setFinalDate(entity.getFinalDate()); // Fecha de retorno (puedes ajustarla según tus necesidades)
            
            // Recuperar el ID del cliente

            // Construir la URI de la entidad creada
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(entity.getId()); // Asumiendo que getId() devuelve la ID de la entidad
            
            super.create(entity);   
            
            return Response.created(uriBuilder.build()).entity(rentalDTO).build();
        }catch(NullPointerException e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing required attribute").build();
        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing required attribute").build();
        }     
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
