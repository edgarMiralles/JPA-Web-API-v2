/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;
import java.util.Collection;
import model.entities.Console;
import model.entities.Game;
import model.entities.GameType;

/**
 *
 * @author edgar
 */
public class PostGameParams {
    private Long consoleId;
    private Collection<Long> typeIds;
    
    public PostGameParams(Game game){
        this.consoleId = game.getConsoleId();
        this.typeIds = game.getTypeIds();
    }
    
    public Long getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(Long consoleId) {
        this.consoleId = consoleId;
    }

    public Collection<Long> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(Collection<Long> typeIds) {
        this.typeIds = typeIds;
    }
    
    public Response handleValidationErrors(EntityManager em){
        if (!typeIds.isEmpty()) {
            int typeValidationResult = validateTypes(em);
            if (typeValidationResult == -1) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("The list of 'types' must contain only positive integers.")
                        .build();
            } else if (typeValidationResult == -2) {
                return Response.status(Response.Status.NOT_FOUND).entity("GameType not Found").build();
            }
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing GameType Parameter").build();
        }

        if (consoleId != null) {
            int consoleValidationResult = validateConsole(em);
            if (consoleValidationResult == -1) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("The 'console' parameter must be a positive integer.")
                        .build();
            } else if (consoleValidationResult == -2) {
                return Response.status(Response.Status.NOT_FOUND).entity("Console not Found").build();
            }
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing Console Parameter").build();
        }

        return null;
    }

    private int validateConsole(EntityManager em) {
        if (consoleId <= 0) {
            return -1;
        }
        if (em.find(Console.class, consoleId) == null) {
            return -2;
        }
        return 0;
    }
    private int validateTypes(EntityManager em) {
        for (Long typeId : typeIds) {
            if (typeId <= 0) {
                return -1;
            }
            if (em.find(GameType.class, typeId) == null) {
                return -2;
            }
        }
        return 0;
    }
}
