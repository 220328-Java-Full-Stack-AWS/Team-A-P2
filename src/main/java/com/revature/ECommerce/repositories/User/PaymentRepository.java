package com.revature.ECommerce.repositories.User;

import com.revature.ECommerce.entities.User.Payment;
import com.revature.ECommerce.repositories.HibernateRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class PaymentRepository implements HibernateRepository<Payment> {

    private Session session;

    public PaymentRepository(Session session){ this.session = session;}
    @Override
    public void save(Payment payment) {
        Transaction tx = session.beginTransaction();
        session.save(payment);
        tx.commit();
    }

    @Override
    public List<Payment> getAll() {
        String hql = "From Payment";
        TypedQuery<Payment> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Payment getById(Integer id) {
        String hql = "FROM Payment WHERE id = :id";
        TypedQuery<Payment> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Payment payment) {
        Transaction tx = session.beginTransaction();
        session.delete(payment);
        tx.commit();
    }
}
