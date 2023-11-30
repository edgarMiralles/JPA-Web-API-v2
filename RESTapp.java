package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.core.Application;

@jakarta.ws.rs.ApplicationPath("webresources")
public class RESTapp extends Application {
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
}
