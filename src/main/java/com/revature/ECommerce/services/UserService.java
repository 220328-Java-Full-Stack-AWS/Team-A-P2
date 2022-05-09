package com.revature.ECommerce.services;

import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.repositories.UserRepository;

import java.util.List;

public class UserService {
    public UserRepository uRepo;

    public UserService(UserRepository uRepo){
        this.uRepo=uRepo;
    }


    public User login(String username, String password){
        User tempUser = uRepo.getByUsername(username);
        if(tempUser==null){
            System.out.println("No Such User exists");
            return null;
        }else if(!tempUser.getPassword().equals(password)){
            System.out.println("Incorrect password");
            return null;
        }
        return tempUser;
    }

    public void register(User user){
        User tempUser = uRepo.getByUsername(user.getUsername());
        if(tempUser== null){
            uRepo.save(user);
        }else{
            System.out.println("That username is not available!");
        }

    }

    public void deleteUser(User user){
        uRepo.deleteUser(user);
    }

    public User getUserById(Integer id){
        return uRepo.getById(id);
    }

    public List<User> getAllUsers(){
        return uRepo.getAll();
    }

    public User getUserByUsername(String username){
        return uRepo.getByUsername(username);
    }

}
