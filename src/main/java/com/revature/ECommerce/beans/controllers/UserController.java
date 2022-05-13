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

    @GetMapping("/allusers")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return uServ.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable String id){
       // return oServ.getById(Integer.parseInt(id));
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User persistOrder(@RequestBody User user, @RequestBody Order order){
       // return uServ.checkOut(user, order);
        return null;
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public User editSalesInOrder(@RequestBody Order order, @RequestBody Sale sale, @RequestHeader("mode")String mode){
      /*  switch (mode){
            case "add":
                return oServ.addToOrder(order, sale);
            case "remove":
                try {
                    return oServ.removeFromOrder(order, sale);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            case "update":
                return oServ.update(order);
            default:
                throw new InvalidOptionException("That isn't a valid option");
        }*/
        return null;
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void delete(User user){
        //uServ.delete(order);
    }
}