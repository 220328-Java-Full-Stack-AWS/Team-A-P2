package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Override
    public User save(User user) {
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User update(User user) {
        User updateUser = this.getById(user.getUserId());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPassword(user.getPassword());
        this.save(updateUser);
        return user;
    }

    public User getByUsername(String username){
        return null;
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
