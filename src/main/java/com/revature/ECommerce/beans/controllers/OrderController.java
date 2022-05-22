package com.revature.ECommerce.beans.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.ECommerce.beans.services.OrderService;
import com.revature.ECommerce.beans.services.UserService;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Sale;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.exceptions.InvalidOptionException;
import com.revature.ECommerce.utilities.HolderClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService oServ;
    private UserService uServ;

    @Autowired
    public OrderController(OrderService oServ, UserService uServ){
        this.uServ=uServ;
        this.oServ=oServ;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders(@PathVariable String userId, @RequestHeader("mode")String mode) throws InvalidOptionException {
        switch(mode){
            case "user":
                return oServ.getByUser(uServ.getUserById(Integer.parseInt(userId)));
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
    public Order persistOrder(@RequestBody HolderClass holder){
        User user= holder.getUser();
        Order order = holder.getOrder();

        order=(oServ.checkOut(holder.getUser(), holder.getOrder()));

        return order;
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Order editSalesInOrder(@RequestBody HolderClass holder, @RequestHeader("mode")String mode){
        Order order = holder.getOrder();
        Sale sale = holder.getSale();
        switch (mode){
            case "add":
                return oServ.addToOrder(order, sale);
            case "remove":
                try {
                    return oServ.removeFromOrder(order, sale);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            default:
                throw new InvalidOptionException("That isn't a valid option");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        oServ.delete(oServ.getById(Integer.parseInt(id)));
    }

}
