package com.revature.ECommerce.beans.controllers;


import com.revature.ECommerce.beans.services.UserService;
import com.revature.ECommerce.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService uServ;

    public UserController(UserService uServ){
        this.uServ=uServ;
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return uServ.getAllUsers();
    }

    @GetMapping("/{usernameOrId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable String usernameOrId, @RequestHeader("mode") String mode) throws Exception {
        switch(mode){
            case "username":
                return uServ.getUserByUsername(usernameOrId);

            case "id":
                return uServ.getUserById(Integer.parseInt(usernameOrId));

            default:
                throw new Exception("Illegall! No can do. pick enter username or id");
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(User user){
        return uServ.register(user);
    }
}
