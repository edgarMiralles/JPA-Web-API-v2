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
import jakarta.persistence.TypedQuery;
import model.entities.Customer;
import model.entities.Game;
import model.entities.Rental;
import model.entities.RentalDTO;

@Stateless
@Path("rental")
public class RentalFacade extends AbstractFacade<Rental> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public RentalFacade() {
        super(Rental.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Rental entity, @Context UriInfo uriInfo) {
        
        TypedQuery<Game> query = (TypedQuery<Game>) em.createNamedQuery("Game.findById");
        query.setParameter("id", entity.getRentedGame().getGameId());
        System.out.println("Hola1111111"+query.getSingleResult());

        
        TypedQuery<Customer> query2 = (TypedQuery<Customer>) em.createNamedQuery("Customer.findById");
        query2.setParameter("id", entity.getTenant().getCustomerId());
        
        System.out.println("Hola22222"+query2.getSingleResult());

                
        entity.setRentedGame(query.getSingleResult());
        entity.setTenant(query2.getSingleResult());
        
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(entity.getId()); // ID de la renta
        rentalDTO.setPrice(entity.getPrice()); // Precio
        rentalDTO.setFinalDate(entity.getFinalDate()); // Fecha de retorno (puedes ajustarla según tus necesidades)
        
        //entity.getTenant().getRentals().add(entity);

        super.create(entity);

        // Construir la URI de la entidad creada
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(entity.getId())); // Asumiendo que getId() devuelve la ID de la entidad

        return Response.created(uriBuilder.build()).entity(super.find(entity.getId())).build();
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
    public Response find(@PathParam("id") Long id) {
        return Response.ok().entity(super.find(id)).build();
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
