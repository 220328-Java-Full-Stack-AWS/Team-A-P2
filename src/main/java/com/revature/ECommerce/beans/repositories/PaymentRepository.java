package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Payment;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

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
        return null;
    }

    @Override
    public List<Payment> getAll() {
        return null;
    }

    @Override
    public Payment getById(Integer id) {
        return null;
    }

    @Override
    public Payment update(Payment payment) {
        return null;
    }
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
