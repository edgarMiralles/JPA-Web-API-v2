package service;

import java.util.List;
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
import jakarta.ws.rs.core.MediaType;
import authn.Secured;
import jakarta.persistence.Query;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Collection;
import model.entities.Console;
import model.entities.Game;
import model.entities.GameType;
import validation.GetGameParams;
import validation.PostGameParams;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Stateless
@Path("game")
public class GameFacadeREST extends AbstractFacade<Game> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public GameFacadeREST() {
        super(Game.class);
    }

    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Game game) {
        super.create(game);
    }

    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createGame(Game game, @Context UriInfo uriInfo) {
        if (gameExists(game.getName(), game.getConsoleId())) {
            return Response.status(Response.Status.CONFLICT).entity("Game already exists").build();
        }
        
        PostGameParams gameValidator = new PostGameParams(game);
        Response validationResponse = gameValidator.handleValidationErrors(em);
        if (validationResponse != null) {
            return validationResponse;
        }
        
        Console console = this.em.find(Console.class, game.getConsoleId());
        game.setConsole(console);

        Query findIn = em.createNamedQuery("GameType.findIn");
        List<GameType> typesList = findIn.setParameter("ids", game.getTypeIds()).getResultList();
        game.setTypes(typesList);

        super.create(game);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Long.toString(game.getId()));

        return Response.created(uriBuilder.build()).entity(super.find(game.getId())).build();
    }

    private boolean gameExists(String name, Long consoleId) {
        Query findByDetails = em.createNamedQuery("Game.findByDetails");
        findByDetails.setParameter("name", name);
        findByDetails.setParameter("consoleId", consoleId);

        List<Game> games = findByDetails.getResultList();

        return !games.isEmpty();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Game entity) {
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findByDetails(
            @BeanParam GetGameParams filterParams) {
        List<Game> games;

        List<Long> typeIds = filterParams.getTypeIds();
        Long consoleId = filterParams.getConsoleId();

        Response validationResponse = filterParams.handleValidationErrors(em);
        if (validationResponse != null) {
            return validationResponse;
        }

        if (!typeIds.isEmpty() && consoleId != null) {
            games = em.createNamedQuery("Game.findByTypesAndConsole", Game.class)
                    .setParameter("typeIds", typeIds)
                    .setParameter("consoleId", consoleId)
                    .getResultList();
        } else if (!typeIds.isEmpty()) {
            games = em.createNamedQuery("Game.findByTypes", Game.class)
                    .setParameter("typeIds", typeIds)
                    .getResultList();
        } else if (consoleId != null) {
            games = em.createNamedQuery("Game.findByConsole", Game.class)
                    .setParameter("consoleId", consoleId)
                    .getResultList();
        } else {
            games = em.createNamedQuery("Game.findAll", Game.class).getResultList();
        }

        if (games.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(games).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("id") Long id) {
            Game game = super.find(id);
            
            game.setConsoleId(game.getConsole().getId());        
            Collection<Long> typeIds = new ArrayList<>();
            for (GameType gameType : game.getTypes()) {
                typeIds.add(gameType.getId());
            }
            game.setTypeIds(typeIds);
    
            return Response.ok().entity(game).build();
    }
    
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Game> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Game> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
