package com.revature.ECommerce.repositories.Product;

import com.revature.ECommerce.entities.Product.Discount;
import com.revature.ECommerce.entities.Product.Inventory;
import com.revature.ECommerce.repositories.HibernateRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class DiscountRepository implements HibernateRepository<Discount> {

    private Session session;

    public DiscountRepository(Session session){this.session = session;}

    @Override
    public void save(Discount discount) {
        Transaction tx = session.beginTransaction();
        session.save(discount);
        tx.commit();
    }

    @Override
    public List<Discount> getAll() {
        return null;
    }

    @Override
    public Discount getById(Integer id) {
        String hql = "FROM Discount WHERE id = :id";
        TypedQuery<Discount> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Discount discount) {
        Transaction tx = session.beginTransaction();
        session.delete(discount);
        tx.commit();
    }
}
