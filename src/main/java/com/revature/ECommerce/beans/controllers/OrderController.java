package com.revature.ECommerce.beans.controllers;

import com.revature.ECommerce.beans.services.OrderService;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Sale;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.exceptions.InvalidOptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService oServ;

    @Autowired
    public OrderController(OrderService oServ){
        this.oServ=oServ;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders(@RequestBody User user, @RequestHeader("mode")String mode) throws InvalidOptionException {
        switch(mode){
            case "user":
                return oServ.getByUser(user);
            case "all":
                return oServ.getAll();
            default:
                throw new InvalidOptionException("That isn't a valid option");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getById(@PathVariable String id){
        return oServ.getById(Integer.parseInt(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Order persistOrder(@RequestBody User user, @RequestBody Order order){
        return oServ.checkOut(user, order);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Order editSalesInOrder(@RequestBody Order order, @RequestBody Sale sale, @RequestHeader("mode")String mode){
        switch (mode){
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
        }
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void delete(Order order){
        oServ.delete(order);
    }
}