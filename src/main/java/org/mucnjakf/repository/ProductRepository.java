package org.mucnjakf.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.mucnjakf.model.Product;

import java.util.List;

public class ProductRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction entityTransaction;

    public ProductRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public List<Product> getProducts() {
        return entityManager.createQuery("SELECT p from Product p", Product.class).getResultList();
    }

    public Product getProduct(int id) {
        return entityManager.find(Product.class, id);
    }

    public boolean insertProduct(Product product) {
        try {
            entityTransaction.begin();

            entityManager.persist(product);

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

    public boolean updateProduct(Product product) {
        try {
            entityTransaction.begin();

            Product productFromDb = entityManager.find(Product.class, product.getId());
            productFromDb.setName(product.getName());
            productFromDb.setPrice(product.getPrice());
            productFromDb.setCategory(product.getCategory());

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

    public boolean deleteProduct(int id) {
        try {
            entityTransaction.begin();

            Product product = entityManager.find(Product.class, id);
            entityManager.remove(product);

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
