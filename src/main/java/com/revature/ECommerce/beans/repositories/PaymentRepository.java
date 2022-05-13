package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.entities.Payment;

import java.util.List;

public class PaymentRepository implements HibernateRepository<Payment> {


    @Override
    public Payment save(Payment payment) {
        return payment;
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