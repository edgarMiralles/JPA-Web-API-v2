/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
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

    public int validateConsole(EntityManager em) {
        if (consoleId <= 0) {
            return -1;
        }
        if (em.find(Console.class, consoleId) == null) {
            return -2;
        }
        return 0;
    }
    public int validateTypes(EntityManager em) {
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
