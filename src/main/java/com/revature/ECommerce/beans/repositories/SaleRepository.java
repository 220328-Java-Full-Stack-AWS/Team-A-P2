package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Sale;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class SaleRepository implements HibernateRepository<Sale>{
    private HibernateManager hibernateManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public SaleRepository(HibernateManager hibernateManager){
        this.hibernateManager=hibernateManager;
    }
    @Override
    public Sale save(Sale sale) {
        return null;
    }

    @Override
    public List<Sale> getAll() {
        return null;
    }

    @Override
    public Sale getById(Integer id) {
        return null;
    }

    @Override
    public Sale update(Sale sale) {
        return null;
    }

    public void delete(Sale sale){
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
