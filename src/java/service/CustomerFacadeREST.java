package service;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.persistence.Query;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import model.entities.Customer;
import model.entities.Rental;

/**
 * Author:  jordi
 * Created: 01 dec 2023
 */

@Stateless
@Path("customer")
public class CustomerFacadeREST extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public CustomerFacadeREST() {
        super(Customer.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Customer entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Customer entity) {
        try {
            if (entity == null) {
                // Si el JSON es nulo, retornar un error
                return Response.status(Response.Status.BAD_REQUEST).entity("The JSON payload is null.").build();
            }

            Query findCustomerId = em.createNamedQuery("Customer.findById");
            findCustomerId.setParameter("id", id);

            try {
                Customer existingCustomer = (Customer) findCustomerId.getSingleResult();

                // Actualizar solo los atributos no nulos
                if (entity.getName() != null) {
                    existingCustomer.setName(entity.getName());
                }
                if (entity.getEmail() != null) {
                    existingCustomer.setEmail(entity.getEmail());
                }
                if (entity.getPassword() != null) {
                    existingCustomer.setPassword(entity.getPassword());
                }

                // Retener solo las rentals que existen en el JSON
                if (entity.getRentals() != null) {
                    existingCustomer.getRentals().retainAll(entity.getRentals());
                }

                super.edit(existingCustomer);

                return Response.status(Response.Status.OK).build();
            } catch (NoResultException e) {
                // Manejar el caso donde no se encuentra el cliente
                return Response.status(Response.Status.NOT_FOUND).entity("There is no customer with id: " + id).build();
            }
        } catch (Exception e) {
            // Manejar otras excepciones
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find() {
                        
        try {
            Query query = em.createNamedQuery("Customer.findCustomers", Customer.class);
            List<Customer> customers = query.getResultList();
            
            for (Customer customer : customers) {
                customer.setPassword(null);
            }

            return Response.ok().entity(customers).build();
        } catch (NoResultException e) {
            // Manejar el caso donde no se encuentra los clientes
            return Response.status(Response.Status.NOT_FOUND).entity("Theres is not customers").build();
        }      

    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        try {
            Query findCustomerId = em.createNamedQuery("Customer.findById"); 
            findCustomerId.setParameter("id",id);
        
            Customer customerFound = (Customer) findCustomerId.getSingleResult();
            customerFound.setPassword(null);
            return Response.ok().entity(customerFound).build();
        } catch (NoResultException e) {
            // Manejar el caso donde no se encuentra el cliente
            return Response.status(Response.Status.NOT_FOUND).entity("Theres is not customers with id: "+id).build();
        }  
    }

    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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