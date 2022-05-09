package com.revature.ECommerce.repo;

import com.revature.ECommerce.entities.ProductInventory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ProductInventoryRepo implements HibernateRepo<ProductInventory> {

    private Session session;

    public ProductInventoryRepo(Session session){ this.session = session;}

    @Override
    public void save(ProductInventory inventory) {
        Transaction tx = session.beginTransaction();
        session.save(inventory);
        tx.commit();
    }

    @Override
    public List<ProductInventory> getAll() {
        String hql = "From ProductInventory";
        TypedQuery<ProductInventory> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public ProductInventory getById(Integer id) {
        String hql = "FROM ProductInventory WHERE id = :id";
        TypedQuery<ProductInventory> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(ProductInventory inventory) {
        Transaction tx = session.beginTransaction();
        session.delete(inventory);
        tx.commit();
    }

}
