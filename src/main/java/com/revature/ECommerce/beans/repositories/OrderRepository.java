package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
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
        String hql = "FROM Order";
        TypedQuery<Order> query = session.createQuery(hql, Order.class);
        return query.getResultList();
    }

    @Override
    public Order getById(Integer id) {
        String hql = "FROM Order WHERE id= :id";
        TypedQuery<Order> query = session.createQuery(hql, Order.class);
        query.setParameter("id", id);
        Order order = query.getSingleResult();
        return order;
    }

    public List<Order> getByUser(User user){
        String hql = "FROM Order WHERE user_id= :user_id";
        TypedQuery<Order> query = session.createQuery(hql, Order.class);
        query.setParameter("user_id", user.getUserId());
        return query.getResultList();
    }

    public boolean orderExists(Order order){
        String hql = "FROM Order WHERE id= :id";
        TypedQuery<Order> query = session.createQuery(hql, Order.class);
        query.setParameter("id", order.getOrderId());
        Order tempOrder= new Order();
        try {
            tempOrder = query.getSingleResult();
        }catch(NoResultException e){
            tempOrder=null;
        }
        return tempOrder != null;
    }

    @Override
    public Order update(Order order) {
        Order updateOrder = this.getById(order.getOrderId());
        this.save(updateOrder);
        return order;
    }

    public void deleteOrder(Order order){
        Transaction tx = session.beginTransaction();
        session.remove(order);
        tx.commit();
    }



    @Override
    public void start() {
        this.session=hibernateManager.getSession();
        running=true;
    }

    @Override
    public void stop() {
        running=false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
