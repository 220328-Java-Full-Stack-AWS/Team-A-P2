package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Sales;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.beans.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService {
    private SalesRepository sRepo;

    @Autowired
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
        sales = sRepo.save(sales);
        purchaser.addPurchase(sales);
        for(Product p : sales.getProductsList()){
            p.setQuantitySold(0);
        }
        return purchaser;
    }

    public Sales addToCart(Product product, Sales sale, int quantity){
        Sales updatedSale = sale;
        Double totalAmount= 0.00;
        List<Product> productList = new ArrayList<>();
        if(product.getProductQuantity()>=quantity && product.getProductStatus().equals("In Stock") && quantity>0) {
            productList.add(product);
            updatedSale.setProductsList(productList);
            product.setProductQuantity(product.getProductQuantity()-quantity);
            totalAmount+= product.getProductPrice()*quantity;
            updatedSale.setAmount(totalAmount);
            return updatedSale;
        }else if(product.getProductQuantity() <=0){
            System.out.println("Unable to add to cart, item is out of stock");
        }else if(quantity<=0){
            System.out.println("please enter a valid quantitiy");
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
