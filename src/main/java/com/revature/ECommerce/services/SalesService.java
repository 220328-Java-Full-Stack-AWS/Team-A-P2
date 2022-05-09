package com.revature.ECommerce.services;

import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Sales;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.repositories.SalesRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class SalesService {
    private SalesRepository sRepo;
    public SalesService(SalesRepository sRepo){
        this.sRepo=sRepo;
    }

    public Sales getSaleById(Integer id){
        return sRepo.getById(id);
    }

    public List<Sales> getSalesByUser(User user){
        return sRepo.getbyUser(user);
    }


    public User checkout(User purchaser, Sales sales){
        sales.setPurchaser(purchaser);
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        sales.setPurchaseDate(timestamp);
        sRepo.save(sales);
        purchaser.addPurchase(sales);
        return purchaser;
    }

    public Sales addToCart(Product product, Sales sale){
        Sales updatedSale = sale;
        Double totalAmount= 0.00;
        if(product.getProductQuantity()>0&& product.getProductStatus().equals("In Stock")) {
            updatedSale.getProductsList().add(product);
            product.setProductQuantity(product.getProductQuantity()-1);
            totalAmount+= product.getProductPrice();
            updatedSale.setAmount(totalAmount);
            return updatedSale;
        }else{
            System.out.println("Unable to add to cart, item is out of stock");
        }
        return null;
    }

    public Sales removeFromCart(Product product, Sales sale){
        Sales updatedSale = sale;
        Double totalAmount= sale.getAmount();
        if(sale.getProductsList().isEmpty()){
            System.out.println("Cart is already empty");
            return updatedSale;
        }else{
            updatedSale.getProductsList().remove(product);
            updatedSale.setAmount(totalAmount - product.getProductPrice());
            product.setProductQuantity(product.getProductQuantity()+1);
            if(product.getProductStatus().equals("Out of Stock")){
                product.setProductStatus("In Stock");
            }
            return updatedSale;
        }
    }

    public void deleteSale(Sales sale){
        sRepo.delete(sale);
    }


}
