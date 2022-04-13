package org.mucnjakf.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.mucnjakf.model.UserLoginHistory;

import java.util.List;

public class UserLoginHistoryRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction entityTransaction;

    public UserLoginHistoryRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public List<UserLoginHistory> getUserLoginHistory() {
        return entityManager.createQuery("SELECT ulh from UserLoginHistory ulh", UserLoginHistory.class).getResultList();
    }

    public void insertUserLoginHistory(UserLoginHistory userLoginHistory) {
        try {
            entityTransaction.begin();

            entityManager.persist(userLoginHistory);

            entityTransaction.commit();
        } finally {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
