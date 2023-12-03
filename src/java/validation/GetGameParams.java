/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.Console;
import model.entities.GameType;

/**
 *
 * @author edgar
 */
public class GetGameParams {
    @QueryParam("console")
    private Long consoleId;

    @QueryParam("types")
    private List<Long> typeIds;

    public Long getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(Long consoleId) {
        this.consoleId = consoleId;
    }

    public List<Long> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(List<Long> typeIds) {
        this.typeIds = typeIds;
    }
    
    public Response handleValidationErrors(EntityManager em) {

        if (!typeIds.isEmpty()) {
            int typeValidationResult = validateTypes(em);
            if (typeValidationResult == -1) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("The list of 'types' must contain only positive integers.")
                        .build();
            } else if (typeValidationResult == -2) {
                return Response.status(Response.Status.NOT_FOUND).entity("GameType not Found").build();
            }
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
        }

        return null; // No se encontraron errores de validación
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
