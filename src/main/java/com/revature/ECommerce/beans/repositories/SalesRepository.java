package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Sales;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class SalesRepository implements HibernateRepository<Sales>{
    private HibernateManager hibernateManager;
    private boolean running = false;
    private Session session;

    @Autowired
    public SalesRepository(HibernateManager hibernateManager){
        this.hibernateManager=hibernateManager;
    }


    @Override
    public Sales save(Sales sales) {
        Transaction tx = session.beginTransaction();
        session.save(sales);
        tx.commit();
        return sales;
    }

    @Override
    public List<Sales> getAll() {
        String hql = "FROM Sales";
        TypedQuery<Sales> query = session.createQuery(hql, Sales.class);
        return query.getResultList();
    }

    @Override
    public Sales getById(Integer id) {
        String hql = "FROM Sales WHERE id= :id";
        TypedQuery<Sales> query = session.createQuery(hql, Sales.class);
        query.setParameter("id", id);
        Sales sale = query.getSingleResult();
        return sale;
    }

    public List<Sales> getbyUser(User user){
        String hql = "FROM Sales WHERE purchaser_id= :purchaser_id";
        TypedQuery<Sales> query = session.createQuery(hql, Sales.class);
        query.setParameter("purchaser_id", user.getUserId());
        return query.getResultList();
    }

    public void delete(Sales sale){
        Transaction tx = session.beginTransaction();
        session.remove(sale);
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
