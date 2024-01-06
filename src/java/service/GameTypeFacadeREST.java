/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import model.entities.GameType;

/**
 *
 * @author Usuario
 */

@Stateless
@Path("gametype")
public class GameTypeFacadeREST extends AbstractFacade<GameType> {
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public GameTypeFacadeREST() {
        super(GameType.class);
    }

    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(GameType gameType) {
        super.create(gameType);
    }

    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createGameType(GameType gameType, @Context UriInfo uriInfo) {
        if (gameTypeExists(gameType.getId())) {
            return Response.status(Response.Status.CONFLICT).entity("GameType already exists").build();
        }
  
        super.create(gameType);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Long.toString(gameType.getId()));

        return Response.created(uriBuilder.build()).entity(super.find(gameType.getId())).build();
    }

    private boolean gameTypeExists(Long gameTypeId) {
        Query findById = em.createNamedQuery("GameType.findById", GameType.class)
                .setParameter("id", gameTypeId);

        GameType gameType = (GameType) findById.getSingleResult();

        return (gameType != null); 
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, GameType entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @Secured
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        return Response.ok().entity(super.find(id)).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") Long id) {
            GameType gameType = super.find(id);
            return Response.ok().entity(gameType).build();
    }

    @GET
    @Path("findByIds")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findByIds(@QueryParam("ids") List<Long> ids) {
        List<GameType> gameTypes = em.createNamedQuery("GameType.findIn", GameType.class)
                .setParameter("ids", ids)
                .getResultList();

        System.out.println(gameTypes);
        
        return Response.ok().entity(gameTypes).build(); 
    }

        
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<GameType> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<GameType> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
