package com.revature.ECommerce.beans.controllers;

import com.revature.ECommerce.beans.services.UserService;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Sale;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.exceptions.InvalidOptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService uServ;

    @Autowired
    public UserController(UserService uServ){
        this.uServ=uServ;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return uServ.getAllUsers();
    }

    @GetMapping("/{idorusername}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable String idorusername, @RequestHeader("mode") String mode) throws InvalidOptionException{
        switch(mode) {
            case "id":
                return uServ.getUserById(Integer.parseInt(idorusername));
            case "username":
                return uServ.getUserByUsername(idorusername);
            default:
                throw new InvalidOptionException("That isn't a valid option");
        }

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User user){
        return uServ.save(user);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public User editUser(@RequestBody User user){
        return uServ.update(user);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestHeader("user_id") String id){
        uServ.delete(Integer.parseInt(id));
    }
}