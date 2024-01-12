package service;

import authn.Secured;
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
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import model.entities.Customer;
import model.entities.CustomerDTO;
import utilities.SecurityUtil;

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
        System.out.println("Gordito: "+entity.getPassword());
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Transactional
    public Response edit(@PathParam("id") String id, Customer entity) {
            if (entity == null) {
                // Si el JSON es nulo, retornar un error
                return Response.status(Response.Status.BAD_REQUEST).entity("The JSON payload is null.").build();
            }

            Query findCustomerId = em.createNamedQuery("Customer.findById");
            findCustomerId.setParameter("id", Long.valueOf(id));

            try {
                Customer existingCustomer = (Customer) findCustomerId.getSingleResult();

                // Actualizar solo los atributos no nulos
                if (entity.getUsername()!= null) {
                    existingCustomer.setUsername(entity.getUsername());
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
        }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find() {
                        
        try {
            Query query = em.createNamedQuery("Customer.findCustomers", Customer.class);
            List<Customer> customers = query.getResultList();
            
            List<CustomerDTO> customerList = new ArrayList<>();
            for (Customer customer : customers) {
                customerList.add(new CustomerDTO(customer));
            }

            return Response.ok().entity(customerList).build();
        } catch (NoResultException e) {
            // Manejar el caso donde no se encuentra los clientes
            return Response.status(Response.Status.NOT_FOUND).entity("Theres is not customers").build();
        }      

    }
    
    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        try {
            Customer customerFound = em.find(Customer.class, id);
            CustomerDTO customer = new CustomerDTO(customerFound);
            return Response.ok().entity(customer).build();
        } catch (NoResultException e) {
            // Manejar el caso donde no se encuentra el cliente
            return Response.status(Response.Status.NOT_FOUND).entity("Theres is not customers with id: "+id).build();
        }  
    }
    
    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findByEmail(@PathParam("email") String email) {
        try {
            Query findCustomerByEmail = em.createNamedQuery("Customer.findByEmail"); 
            findCustomerByEmail.setParameter("email",email);
            Customer customerFound = (Customer) findCustomerByEmail.getSingleResult();
                        return Response.ok().entity(customerFound).build();
        } catch (NoResultException e) {
            // Manejar el caso donde no se encuentra el cliente
            return Response.status(Response.Status.NOT_FOUND).entity("Theres is not customers with id: "+email).build();
        }  
    }
    
    @POST
    @Path("validate")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findByEmail(Customer customer) {
        if (customer.getEmail() == null || customer.getPassword() == null) {
            // Datos insuficientes proporcionados
            return Response.status(Response.Status.BAD_REQUEST).entity("Email y contraseña requeridos").build();
        }
        try {
            Query findCustomerByEmail = em.createNamedQuery("Customer.findByEmail"); 
            findCustomerByEmail.setParameter("email",customer.getEmail());
            Customer foundCustomer = (Customer) findCustomerByEmail.getSingleResult();
            System.out.println("Nueva "+customer.getPassword());
            System.out.println("Guardada "+foundCustomer.getPassword());

            if (SecurityUtil.validatePassword(customer.getPassword(), foundCustomer.getPassword())) {
                System.out.println("Soy una mosca");
                return Response.ok(foundCustomer).build();
            } else {
                // Cliente no encontrado o contraseña incorrecta
                return Response.status(Response.Status.UNAUTHORIZED).entity("Cliente no encontrado o contraseña incorrecta").build();
            }
        } catch (Exception e) {
            // Manejo de excepciones, como NoResultException
            return Response.status(Response.Status.UNAUTHORIZED).entity("Cliente no encontrado o contraseña incorrecta").build();
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