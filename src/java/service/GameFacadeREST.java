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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import model.entities.Console;
import model.entities.Game;
import model.entities.GameType;
import validation.GetGameParams;

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
            //409
            return Response.status(Response.Status.CONFLICT).entity("Game already exists").build();
        }

        try{
            Console console = this.em.find(Console.class, game.getConsoleId());
            if (console == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Console not Found").build();
            }
            game.setConsole(console);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing Console Parameter").build();
        }

        try {
            Query findIn = em.createNamedQuery("GameType.findIn");
            List<GameType> typesList = findIn.setParameter("ids", game.getTypeIds()).getResultList();
            if (typesList.size() != game.getTypeIds().size()) {
                return Response.status(Response.Status.NOT_FOUND).entity("One Gametype not Found").build();
            }
            game.setTypes(typesList);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing GameType Parameter").build();
        }

        super.create(game);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(game.getId()));

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

        Response validationResponse = handleValidationErrors(filterParams);
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

    private Response handleValidationErrors(GetGameParams filterParams) {
        List<Long> typeIds = filterParams.getTypeIds();
        Long consoleId = filterParams.getConsoleId();

        if (!typeIds.isEmpty()) {
            int typeValidationResult = filterParams.validateTypes(em);
            if (typeValidationResult == -1) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("The list of 'types' must contain only positive integers.")
                        .build();
            } else if (typeValidationResult == -2) {
                return Response.status(Response.Status.NOT_FOUND).entity("GameType not Found").build();
            }
        }

        if (consoleId != null) {
            int consoleValidationResult = filterParams.validateConsole(em);
            if (consoleValidationResult == -1) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("The 'console' parameter must be a positive integer.")
                        .build();
            } else if (consoleValidationResult == -2) {
                return Response.status(Response.Status.NOT_FOUND).entity("Console not Found").build();
            }
        }

        return null; // No se encontraron errores de validación
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
