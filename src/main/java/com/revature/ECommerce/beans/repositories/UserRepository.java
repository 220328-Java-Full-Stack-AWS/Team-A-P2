package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserRepository implements HibernateRepository<User> {
    private HibernateManager hibernateManager;
    private Session session;
    boolean running = false;

    @Autowired
    public UserRepository(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    @Override
    public User save(User user) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(user);
        tx.commit();
        return user;
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = session.createQuery("FROM User");
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public User getById(Integer id) {
        TypedQuery<User> query = session.createQuery("FROM User WHERE user_id = :user_id", User.class);
        query.setParameter("user_id", id);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public User update(User user) {
        User updateUser = this.getById(user.getUserId());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        this.save(updateUser);
        return user;
    }

    public User getByUsername(String username) {
        TypedQuery<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    public User getByEmail(String email) {
        TypedQuery<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public User getByPhone(String phone) {
        TypedQuery<User> query = session.createQuery("FROM User WHERE phone = :phone", User.class);
        query.setParameter("phone", phone);
        return query.getSingleResult();
    }

    public void delete(Integer id){
        Transaction tx = session.beginTransaction();
        TypedQuery<User> query = session.createQuery("DELETE User WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        tx.commit();
    }

    @Override
    public void start() {
        this.session=hibernateManager.getSession();
        running=true;
    }

    @Override
    public void stop() {
        running = false;
    }


    @Override
    public boolean isRunning() {
        return running;
    }
}
