package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.OrderRepository;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Sale;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.exceptions.EmptyCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository oRepo;
    private UserService uServ;
    private SaleService sServ;

    @Autowired
    public OrderService(OrderRepository oRepo, UserService uServ, SaleService sServ){
        this.uServ=uServ;
        this.oRepo=oRepo;
        this.sServ=sServ;
    }

    public Order addToOrder(Order order, Sale sale) {
        if(oRepo.orderExists(order)){
            List<Sale> tempList=new ArrayList<>();
            sale.setOrder(order);
            tempList.add(sale);
            order.setSaleList(tempList);
            return order;
        }else{
            //order=oRepo.save(order);
            List<Sale> tempList=new ArrayList<>();
            sale.setOrder(order);
            tempList.add(sale);
            order.setSaleList(tempList);
            order=oRepo.update(order);
            return order;
        }
    }

    public Order removeFromOrder(Order order, Sale sale) throws Exception {
        if(oRepo.orderExists(order)) {
            List<Sale> tempList = new ArrayList<>();
            tempList = order.getSaleList();
            if (tempList != null && !tempList.isEmpty()) {
                tempList.remove(sale);
                order.setSaleList(tempList);
                sServ.delete(sale);
                return order;
            }
            throw new Exception("This order has nothing to remove");
        }else{
            throw new Exception("There is no such Order");
        }
    }

    public Order checkOut(User user, Order order){
        if(order.getSaleList()== null || order.getSaleList().isEmpty()){
            throw new EmptyCartException("Can't checkout an empty cart");
        }/*else {
            order.setUser(user);
            List<Order> tempList = new ArrayList<>();
            tempList.add(order);
            user.setListOfOrders(tempList);
            if(oRepo.orderExists(order)) {
                uServ.save(user);
                order = oRepo.update(order);
            } else {
                oRepo.save(order);
            }
            uServ.save(user);
            return order;
        }*/
        return null;
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

    public List<Order> getAll(){
        return oRepo.getAll();
    }

    public Order update(Order order){
        return oRepo.update(order);
    }

}
