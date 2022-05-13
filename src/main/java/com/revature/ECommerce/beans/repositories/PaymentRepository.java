package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Address;
import com.revature.ECommerce.entities.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PaymentRepository implements HibernateRepository<Payment> {
    private final HibernateManager hibernateManager;
    private boolean running = false;
    public Session session;
    private String tableName;

    @Autowired
    public PaymentRepository(HibernateManager hibernateManager){
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
        TypedQuery<Payment> query = session.createQuery(hql, Payment.class);
        return query.getResultList();
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
        updatePayment.setPaymentId(payment.getPaymentId());
        updatePayment.setCardNumber(payment.getCardNumber());
        updatePayment.setExperationDate(payment.getExperationDate());
        updatePayment.setCvc(payment.getCvc());
        updatePayment.setUser(payment.getUser());
        this.save(updatePayment);
        return payment;
    }

    public void delete(Payment payment){
        Transaction tx = session.beginTransaction();
        session.remove(payment);
        tx.commit();
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

    @Value("payment")
    public void setTableName(String tableName){
        this.tableName = tableName;
    }

    public List<Payment> getPaymentsByUser(Integer user_id){
        String hql = "FROM Payment WHERE user_id = :user_id";
        TypedQuery<Payment> query = session.createQuery(hql, Payment.class);
        query.setParameter("user_id", user_id);
        return query.getResultList();
    }
}
