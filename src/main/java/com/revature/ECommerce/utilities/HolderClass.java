package com.revature.ECommerce.utilities;

import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Sale;
import com.revature.ECommerce.entities.User;

public class HolderClass {
    private Order order;
    private Sale sale;
    private User user;
    public HolderClass(){

    }
    public HolderClass(Order order, Sale sale, User user){
        this.sale=sale;
        this.order=order;
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
