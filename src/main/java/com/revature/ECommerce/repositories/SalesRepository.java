package com.revature.ECommerce.repositories;

import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Sales;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SalesRepository implements HibernateRepository<Sales>{
    private Session session;

    public SalesRepository(Session session){
        this.session=session;
    }


    @Override
    public void save(Sales sales) {

    }

    @Override
    public List<Sales> getAll() {
        return null;
    }

    @Override
    public Sales getById(Integer id) {
        return null;
    }

    public List<Sales> getbyUser(User user){
        return null;
    }

    public void delete(Sales sale){
        Transaction tx = session.beginTransaction();
        session.remove(sale);
        tx.commit();
    }



}
