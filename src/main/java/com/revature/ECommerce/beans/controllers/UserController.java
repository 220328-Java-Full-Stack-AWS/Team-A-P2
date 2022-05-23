package com.revature.ECommerce.beans.controllers;

import com.revature.ECommerce.beans.services.UserService;
import com.revature.ECommerce.dtos.AuthDto;
import com.revature.ECommerce.entities.Address;
import com.revature.ECommerce.entities.Payment;
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

    @GetMapping("/{path}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable String path, @RequestHeader("mode") String mode) throws InvalidOptionException {
        switch(mode) {
            case "id":
                return uServ.getUserById(Integer.parseInt(path));
            case "username":
                return uServ.getUserByUsername(path);
            case "email":
                return uServ.getUserByEmail(path);
            case "phone":
                return uServ.getUserByPhone(path);
            default:
                throw new InvalidOptionException("That isn't a valid option");
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User loginOrRegister(@RequestBody User user, @RequestHeader("mode") String mode) throws Exception {
        switch(mode) {
            case "register":
                return uServ.save(user);
            case "login":
                AuthDto auth = new AuthDto(user.getUsername(), user.getPassword());
                return uServ.authenticateUser(auth);
            default:
                throw new InvalidOptionException("That isn't a valid option");
        }

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