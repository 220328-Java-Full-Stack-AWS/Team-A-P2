package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepository implements HibernateRepository<User>{
    private HibernateManager hibernateManager;
    private Session session;
    boolean running = false;

    @Autowired
    public UserRepository(HibernateManager hibernateManager){
        this.hibernateManager=hibernateManager;
    }

    //Thanks to Hibernate this performs both the Create and Update functionalities
    @Override
    public User save(User user) {
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        return user;
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

    public boolean checkUsernameExists(String username) {
        String hql = "FROM User WHERE username = :username";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        query.setParameter("username", username);
        List<User> resultsList = query.getResultList();
        if(resultsList.isEmpty()) {
            return false;
        }
        return true;
    }
    public void deleteUser(User user){
        Transaction tx = session.beginTransaction();
        session.remove(user);
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
