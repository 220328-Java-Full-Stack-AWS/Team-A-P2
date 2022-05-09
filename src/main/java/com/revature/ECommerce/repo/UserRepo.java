package com.revature.ECommerce.repo;

import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepo implements HibernateRepo<User> {

    private Session session;

    public UserRepo(Session session){this.session = session;}

    @Override
    public void save(User user) {
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
    }

    @Override
    public List<User> getAll() {
        String hql = "From User";
        TypedQuery<User> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public User getById(Integer id) {
        String hql = "FROM User WHERE id = :id";
        TypedQuery<User> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(User user) {
        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
    }

    public User getByUsername(String username){
        String hql = "FROM User WHERE username = :username";
        TypedQuery<User> query = session.createQuery(hql);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

}
