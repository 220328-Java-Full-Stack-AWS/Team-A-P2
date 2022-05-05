package com.revature.ECommerce.repositories;

import com.revature.ECommerce.entities.User;

import java.util.List;

public class UserRepository implements HibernateRepository<User>{

    @Override
    public void save(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    public User getByUsername(String username){
        return null;
    }
}
