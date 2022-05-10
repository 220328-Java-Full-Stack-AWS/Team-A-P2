package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductRepository implements HibernateRepository<Product> {
    private HibernateManager hibernateManager;
    boolean running = false;
    private Session session;

    @Autowired
    public ProductRepository(HibernateManager hibernateManager){
        this.hibernateManager=hibernateManager;
    }

    @Override
    public Product save(Product product) {
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        return product;
    }

    @Override
    public List<Product> getAll() {
        String hql = "FROM Product";
        TypedQuery<Product> query = session.createQuery(hql, Product.class);
        return query.getResultList();
    }

    @Override
    public Product getById(Integer id) {
        String hql = "FROM Product WHERE id= :id";
        TypedQuery<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("id", id);
        Product product = query.getSingleResult();
        return product;
    }

    public void deleteProduct(Product product){
        Transaction tx = session.beginTransaction();
        session.remove(product);
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
