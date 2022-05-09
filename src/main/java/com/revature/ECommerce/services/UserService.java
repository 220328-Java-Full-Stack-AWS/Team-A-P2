package com.revature.ECommerce.services;

import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.repositories.UserRepository;

public class UserService {
    public UserRepository uRepo;

    public UserService(UserRepository uRepo){
        this.uRepo=uRepo;
    }


    public User login(String username, String Password){
        return null;
    }

    public User register(User user){
        return null;
    }

}
