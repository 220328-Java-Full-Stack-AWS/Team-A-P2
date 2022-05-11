package com.revature.ECommerce.services;

import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.repo.UserRepo;
import org.hibernate.Session;

public class AuthenticationService {

    private final UserRepo userRepo;
    public AuthenticationService(UserRepo userRepo){ this.userRepo = userRepo; }

    public User login(String username, String password){
        User user = userRepo.getByUsername(username);
        if(user.getId() == 0){
            System.out.println("Username not found");
        }else if(!(user.getPassword().equals(password))){
            System.out.println("Wrong password");
        }else{
            System.out.println("Logged In");

        }
        return user;
    }
}
