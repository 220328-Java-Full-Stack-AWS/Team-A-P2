package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Sale;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class SaleRepository implements HibernateRepository<Sale> {
    private HibernateManager hibernateManager;
    private Session session;
    private boolean running = false;

    private String tableName;

    @Autowired
    public SaleRepository(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    @Override
    public Sale save(Sale sale) {
        Transaction tx = session.beginTransaction();
        session.save(sale);
        tx.commit();
        return sale;
    }

    @Override
    public List<Sale> getAll() {
        String hql = "FROM Sale";
        TypedQuery<Sale> query = session.createQuery(hql, Sale.class);
        return query.getResultList();
    }

    @Override
    public Sale getById(Integer id) {
        String hql = "FROM Sale WHERE sales_id = :id";
        TypedQuery<Sale> query = session.createQuery(hql, Sale.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Sale update(Sale sale) {
        Sale updateSale = this.getById(sale.getSaleId());
        updateSale.setSaleId(sale.getSaleId());
        updateSale.setQuantity(sale.getQuantity());
        updateSale.setCost(sale.getCost());
        updateSale.setQuantity(sale.getQuantity());
        updateSale.setDateOfPurchase(sale.getDateOfPurchase());
        updateSale.setProduct(sale.getProduct());
        //updateSale.setOrder(sale.getOrder());
        this.save(updateSale);
        return sale;
    }

    public void delete(Sale sale) {
        Transaction tx = session.beginTransaction();
        session.remove(session.get(Sale.class, sale.getSaleId()));
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

    public List<Sale> getAllSalesWithProductId(Integer id) {
        String hql = "FROM Sale WHERE product_id = :id";
        TypedQuery<Sale> query = session.createQuery(hql, Sale.class);
        return query.getResultList();
    }

    public Sale getSaleByOrderId(Integer id) {
        String hql = "FROM Sale WHERE order_id = :id";
        TypedQuery<Sale> query = session.createQuery(hql, Sale.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Value("sales")
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
