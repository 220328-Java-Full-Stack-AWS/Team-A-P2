package com.revature.ECommerce.repo;

import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.entities.UserAddress;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserAddressRepo implements HibernateRepo<UserAddress> {

    private Session session;

    public UserAddressRepo(Session session){ this.session = session;}

    @Override
    public void save(UserAddress address) {
        Transaction tx = session.beginTransaction();
        session.save(address);
        tx.commit();
    }

    @Override
    public List<UserAddress> getAll() {
        String hql = "From UserAddress";
        TypedQuery<UserAddress> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public UserAddress getById(Integer id) {
        String hql = "FROM UserAddress WHERE id = :id";
        TypedQuery<UserAddress> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(UserAddress address) {
        Transaction tx = session.beginTransaction();
        session.delete(address);
        tx.commit();
    }

    public List<UserAddress> getByState(String state){
        String hql = "FROM UserAddress WHERE state = :state";
        TypedQuery<UserAddress> query = session.createQuery(hql);
        query.setParameter("state", state);
        return query.getResultList();
    }

    public List<UserAddress> getByCity(String city){
        String hql = "FROM UserAddress WHERE city = :city";
        TypedQuery<UserAddress> query = session.createQuery(hql);
        query.setParameter("city", city);
        return query.getResultList();
    }

    public List<UserAddress> getByZip(String zip){
        String hql = "FROM UserAddress WHERE zip = :zip";
        TypedQuery<UserAddress> query = session.createQuery(hql);
        query.setParameter("zip", zip);
        return query.getResultList();
    }

    public List<UserAddress> getByCountry(String country){
        String hql = "FROM UserAddress WHERE country = :country";
        TypedQuery<UserAddress> query = session.createQuery(hql);
        query.setParameter("country", country);
        return query.getResultList();
    }
}
