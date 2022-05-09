package com.revature.ECommerce.repo;

import com.revature.ECommerce.entities.UserAddress;
import com.revature.ECommerce.entities.UserPayment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserPaymentRepo implements HibernateRepo<UserPayment> {

    private Session session;

    public UserPaymentRepo(Session session){ this.session = session;}

    @Override
    public void save(UserPayment payment) {
        Transaction tx = session.beginTransaction();
        session.save(payment);
        tx.commit();
    }

    @Override
    public List<UserPayment> getAll() {
        String hql = "From UserPayment";
        TypedQuery<UserPayment> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public UserPayment getById(Integer id) {
        String hql = "FROM UserPayment WHERE id = :id";
        TypedQuery<UserPayment> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(UserPayment payment) {
        Transaction tx = session.beginTransaction();
        session.delete(payment);
        tx.commit();
    }

    public List<UserPayment> getByProvider(String provider){
        String hql = "FROM UserPayment WHERE provider = :provider";
        TypedQuery<UserPayment> query = session.createQuery(hql);
        query.setParameter("provider", provider);
        return query.getResultList();
    }

    public List<UserPayment> getByType(String payment_type){
        String hql = "FROM UserPayment WHERE payment_type = :payment_type";
        TypedQuery<UserPayment> query = session.createQuery(hql);
        query.setParameter("payment_type", payment_type);
        return query.getResultList();
    }
}
