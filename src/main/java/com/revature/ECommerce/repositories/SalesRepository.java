package com.revature.ECommerce.repositories;

import com.revature.ECommerce.entities.Sales;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class SalesRepository implements HibernateRepository<Sales>{
    private Session session;

    public SalesRepository(Session session){
        this.session=session;
    }


    @Override
    public void save(Sales sales) {
        Transaction tx = session.beginTransaction();
        session.save(sales);
        tx.commit();
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



}
