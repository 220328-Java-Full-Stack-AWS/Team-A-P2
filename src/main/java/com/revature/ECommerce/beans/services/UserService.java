package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.UserRepository;
import com.revature.ECommerce.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository uRepo;

    @Autowired
    public UserService(UserRepository uRepo){this.uRepo=uRepo;}
    public User save(User user){
        return uRepo.save(user);
    }
}
