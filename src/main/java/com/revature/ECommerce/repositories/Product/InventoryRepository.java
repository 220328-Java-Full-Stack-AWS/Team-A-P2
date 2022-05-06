package com.revature.ECommerce.repositories.Product;

import com.revature.ECommerce.entities.Product.Inventory;
import com.revature.ECommerce.repositories.HibernateRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class InventoryRepository implements HibernateRepository<Inventory> {

    private Session session;

    public InventoryRepository(Session session){ this.session = session;}

    @Override
    public void save(Inventory inventory) {
        Transaction tx = session.beginTransaction();
        session.save(inventory);
        tx.commit();
    }

    @Override
    public List<Inventory> getAll() {
        String hql = "From Inventory";
        TypedQuery<Inventory> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Inventory getById(Integer id) {
        String hql = "FROM Inventory WHERE id = :id";
        TypedQuery<Inventory> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Inventory inventory) {
        Transaction tx = session.beginTransaction();
        session.delete(inventory);
        tx.commit();
    }
}
