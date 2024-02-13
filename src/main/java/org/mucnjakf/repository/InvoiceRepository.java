package org.mucnjakf.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.mucnjakf.model.Invoice;
import org.mucnjakf.model.Product;

import java.util.List;

public class InvoiceRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction entityTransaction;

    public InvoiceRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public List<Invoice> getInvoices() {
        return entityManager.createQuery("SELECT i from Invoice i", Invoice.class).getResultList();
    }

    public Invoice getInvoice(int id) {
        return entityManager.find(Invoice.class, id);
    }

    public boolean insertInvoice(Invoice invoice, List<Product> products) {
        try {
            entityTransaction.begin();
            entityManager.persist(invoice);

            for (Product product : products) {
                Product productFromDb = entityManager.find(Product.class, product.getId());
                productFromDb.getInvoices().add(invoice);
                invoice.getProducts().add(productFromDb);
            }

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
