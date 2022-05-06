package com.revature.ECommerce.repositories.User;

import com.revature.ECommerce.entities.User.Address;
import com.revature.ECommerce.repositories.HibernateRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class AddressRepository implements HibernateRepository<Address> {

    private Session session;

    public AddressRepository(Session session){ this.session = session; }

    @Override
    public void save(Address address) {
        Transaction tx = session.beginTransaction();
        session.save(address);
        tx.commit();
    }

    @Override
    public List<Address> getAll() {
        String hql = "From Address";
        TypedQuery<Address> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Address getById(Integer id) {
        String hql = "FROM Address WHERE id = :id";
        TypedQuery<Address> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Address address) {
        Transaction tx = session.beginTransaction();
        session.delete(address);
        tx.commit();
    }
}
