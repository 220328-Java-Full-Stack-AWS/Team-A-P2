package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.OrderRepository;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Sale;
import com.revature.ECommerce.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository oRepo;

    @Autowired
    public OrderService(OrderRepository oRepo){
        this.oRepo=oRepo;
    }

    public Order addToOrder(Order order, Sale sale) throws Exception {
        if(oRepo.orderExists(order)){
            List<Sale> tempList=new ArrayList<>();
            tempList.add(sale);
            order.setSaleList(tempList);
            return order;
        }else{
            throw new Exception("There is no such order");
        }
    }

    public Order removeFromOrder(Order order, Sale sale) throws Exception {
        if(oRepo.orderExists(order)) {
            List<Sale> tempList = new ArrayList<>();
            tempList = order.getSaleList();
            if (tempList != null && !tempList.isEmpty()) {
                tempList.remove(sale);
                order.setSaleList(tempList);
                return order;
            }
            throw new Exception("This order has nothing to remove");
        }else{
            throw new Exception("There is no such Order");
        }
    }

    public Order checkOut(User user, Order order){
        order.setUser(user);
        List<Order>tempList=new ArrayList<>();
        tempList.add(order);
        user.setListOfOrders(tempList);
        if(oRepo.orderExists(order)){
            oRepo.update(order);
            //save user
            return order;
        }else{
            oRepo.save(order);
            //save user
            return order;
        }
    }

    public void delete(Order order){
        oRepo.deleteOrder(order);
    }

    public Order getById(Integer id){
        return oRepo.getById(id);
    }

    public List<Order> getByUser(User user){
        return oRepo.getByUser(user);
    }

    public Order update(Order order){
        return oRepo.update(order);
    }


}
