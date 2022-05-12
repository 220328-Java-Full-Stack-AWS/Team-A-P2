package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderRepository implements HibernateRepository<Order>{
    private HibernateManager hibernateManager;
    boolean running = false;
    private Session session;

    @Autowired
    public OrderRepository(HibernateManager hibernateManager){
        this.hibernateManager=hibernateManager;
    }
    @Override
    public Order save(Order order) {
        Transaction tx = session.beginTransaction();
        session.save(order);
        tx.commit();
        return order;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order getById(Integer id) {
        return null;
    }

    @Override
    public Order update(Order order) {
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
