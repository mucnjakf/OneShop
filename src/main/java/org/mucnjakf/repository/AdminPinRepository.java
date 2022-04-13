package org.mucnjakf.repository;

import org.mucnjakf.model.AdminPin;
import jakarta.persistence.*;

public class AdminPinRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public AdminPinRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public AdminPin getAdminPin() {
        return entityManager.createQuery("SELECT a FROM AdminPin a", AdminPin.class).getResultList().get(0);
    }
}
