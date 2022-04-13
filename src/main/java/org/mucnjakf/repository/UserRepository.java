package org.mucnjakf.repository;

import org.mucnjakf.model.User;
import jakarta.persistence.*;

import java.util.List;

public class UserRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction entityTransaction;

    public UserRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    public boolean insertUser(User user) {
        try {
            entityTransaction.begin();

            entityManager.persist(user);

            entityTransaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public boolean updateUser(User user) {
        try {
            entityTransaction.begin();

            User userFromDb = entityManager.find(User.class, user.getId());
            userFromDb.setUsername(user.getUsername());
            userFromDb.setPasswordSalt(user.getPasswordSalt());
            userFromDb.setPasswordHash(user.getPasswordHash());
            userFromDb.setFirstName(user.getFirstName());
            userFromDb.setLastName(user.getLastName());
            userFromDb.setEmail(user.getEmail());

            entityTransaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public boolean deleteUser(int id) {
        try {
            entityTransaction.begin();

            User user = entityManager.find(User.class, id);
            entityManager.remove(user);

            entityTransaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
