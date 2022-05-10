package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.datatransferobjects.AuthDTO;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.beans.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private UserRepository uRepo;

    @Autowired
    public UserService(UserRepository uRepo){
        this.uRepo=uRepo;
    }


    public User login(AuthDTO authDTO){
        User temp = uRepo.getByUsername(authDTO.getUsername());
        if(temp != null && temp.getPassword().equals(authDTO.getPassword())){
            return  temp;
        }else{
            throw new RuntimeException("Incorrect Password");
        }

    }

    public User register(User user){
        if(!uRepo.checkUsernameExists(user.getUsername())){
           return uRepo.save(user);
        }else{
            System.out.println("That username is not available!");
            return null;
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
