package com.revature.ECommerce.repositories;

import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepository implements HibernateRepository<User>{
    private Session session;
    public UserRepository(Session session){
        this.session=session;
    }

    //Thanks to Hibernate this performs both the Create and Update functionalities
    @Override
    public void save(User user) {
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
    }

    //This allows us to get all users currently in the DB
    @Override
    public List<User> getAll() {
        String hql = "FROM User";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        return query.getResultList();
    }

    //This allows us to get a specific user by their user id
    @Override
    public User getById(Integer id) {
        String hql = "FROM User WHERE id= :id";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        query.setParameter("id", id);
        User user = query.getSingleResult();
        return user;
    }

    //This allows us to get a specific user by their username
    public User getByUsername(String username){
        String hql = "FROM User WHERE username = :username";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();
        return user;
    }
    public void deleteUser(User user){
        Transaction tx = session.beginTransaction();
        session.remove(user);
        tx.commit();
    }
}
