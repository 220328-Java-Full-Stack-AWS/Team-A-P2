package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.util.List;

public class PaymentRepository implements HibernateRepository<Payment> {
    private HibernateManager hibernateManager;
    private Session session;
    boolean running = false;

    @Autowired
    public PaymentRepository(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    @Override
    public Payment save(Payment payment) {
        Transaction tx = session.beginTransaction();
        session.save(payment);
        tx.commit();
        return payment;
    }

    @Override
    public List<Payment> getAll() {
        String hql = "FROM Payment";
        TypedQuery<Payment> query = session.createQuery( hql, Payment.class);
        return null;
    }

    @Override
    public Payment getById(Integer id) {
        String hql = "FROM Payment WHERE id = :id";
        TypedQuery<Payment> query = session.createQuery( hql, Payment.class);

        query.setParameter("id", id);

        Payment pay = query.getSingleResult();
        return pay;
    }

    @Override
    public Payment update(Payment payment) {
        Payment updatePayment = this.getById(payment.getPaymentId());
        updatePayment.setUser(payment.getUser());
        updatePayment.setCardNumber(payment.getCardNumber());
        updatePayment.setExpirationDate(payment.getExpirationDate());
        updatePayment.setCvc(payment.getCvc());
        this.save(updatePayment);
        return updatePayment;
    }

    @Override
    public void start() {
        this.session = hibernateManager.getSession();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
