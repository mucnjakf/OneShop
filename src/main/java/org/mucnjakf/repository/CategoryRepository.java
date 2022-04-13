package org.mucnjakf.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.mucnjakf.model.Category;

import java.util.List;

public class CategoryRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction entityTransaction;

    public CategoryRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public List<Category> getCategories() {
        return entityManager.createQuery("SELECT c from Category c", Category.class).getResultList();
    }

    public Category getCategory(int id) {
        return entityManager.find(Category.class, id);
    }

    public boolean insertCategory(Category category) {
        try {
            entityTransaction.begin();

            entityManager.persist(category);

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

    public boolean updateCategory(Category category) {
        try {
            entityTransaction.begin();

            Category categoryFromDb = entityManager.find(Category.class, category.getId());
            categoryFromDb.setName(category.getName());

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

    public boolean deleteCategory(int id) {
        try {
            entityTransaction.begin();

            Category category = entityManager.find(Category.class, id);
            entityManager.remove(category);

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
