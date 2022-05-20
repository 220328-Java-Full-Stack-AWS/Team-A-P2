package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Address;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AddressRepository implements HibernateRepository<Address>{
    private HibernateManager hibernateManager;
    private boolean running = false;
    private Session session;
    private String tableName;

    @Autowired
    public AddressRepository(HibernateManager hibernateManager){
        this.hibernateManager = hibernateManager;
    }

    @Override
    public Address save(Address address) {
        Transaction tx = session.beginTransaction();
        session.save(address);
        tx.commit();
        return address;
    }

    @Override
    public List<Address> getAll() { //Do we even need a get all for address? I assume realistically only need a get by Username
        return null;
    }

    @Override
    public Address getById(Integer id) {
        String hql = "From Address WHERE id = :id";
        TypedQuery<Address> query = session.createQuery(hql, Address.class);
        query.setParameter("id",id);
        Address address =query.getSingleResult();
        return address;
    }

    public Address getByUsername(User user){ // I'm not 100% how User gets connected to Address table and if this look up is possible
        String username = user.getUsername();
        String hql = "FROM Address WHERE username = :username";
        TypedQuery<Address> query = session.createQuery(hql, Address.class);
        query.setParameter("username",username);
        Address address = query.getSingleResult();
        return address;
    }

    @Override
    public Address update(Address address) {
        Address updateAddress = this.getById(address.getAddressId());
        updateAddress.setAddressId(address.getAddressId());
        updateAddress.setAddress(address.getAddress());
        updateAddress.setCity(address.getCity());
        updateAddress.setState(address.getState());
        updateAddress.setZipCode(address.getZipCode());
        updateAddress.setCountry(address.getCountry());
        updateAddress.setUser(address.getUser());
        this.save(updateAddress);
        return address;
    }

    public void delete(Address address){
        Transaction tx = session.beginTransaction();
        session.remove(address);
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

    public List<Address> getByState(String state){
        String hql = "FROM Address WHERE state = :state";
        TypedQuery<Address> query = session.createQuery(hql, Address.class);
        query.setParameter("state", state);
        return query.getResultList();
    }

    public List<Address> getByCountry(String country){
        String hql = "FROM Address WHERE country = :country";
        TypedQuery<Address> query = session.createQuery(hql, Address.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

    public List<Address> getByCity(String city){
        String hql = "FROM Address WHERE city = :city";
        TypedQuery<Address> query = session.createQuery(hql, Address.class);
        query.setParameter("city", city);
        return query.getResultList();
    }

    public List<Address> getByZipCode(Integer zip){
        String hql = "FROM Address WHERE zip = :zip";
        TypedQuery<Address> query = session.createQuery(hql, Address.class);
        query.setParameter("zip", zip);
        return query.getResultList();
    }

    public List<Address> getByUserId(Integer user_id){
        String hql = "FROM Address WHERE user_id = :user_id";
        TypedQuery<Address> query = session.createQuery(hql, Address.class);
        query.setParameter("user_id", user_id);
        return query.getResultList();
    }

    @Value("address")
    public void setTableName(String tableName){
        this.tableName = tableName;
    }
}
