package com.revature.ECommerce.repo;

import com.revature.ECommerce.entities.Discount;
import com.revature.ECommerce.entities.UserPayment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class DiscountRepo implements HibernateRepo<Discount> {

    private Session session;

    public DiscountRepo(Session session){ this.session = session;}

    @Override
    public void save(Discount discount) {
        Transaction tx = session.beginTransaction();
        session.save(discount);
        tx.commit();
    }

    @Override
    public List<Discount> getAll() {
        String hql = "From Discount";
        TypedQuery<Discount> query = session.createQuery(hql);
        return query.getResultList();
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

    public Discount getByName(String discount_name){
        String hql = "FROM Discount WHERE discount_name = :discount_name";
        TypedQuery<Discount> query = session.createQuery(hql);
        query.setParameter("discount_name", discount_name);
        return query.getSingleResult();
    }

    public List<Discount> getByActive(Boolean active) {
        String hql = "FROM Discount WHERE active = :active";
        TypedQuery<Discount> query = session.createQuery(hql);
        query.setParameter("active", active);
        return query.getResultList();
    }
}
