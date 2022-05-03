package com.revature;

import com.revature.Models.Product;
import com.revature.Models.User;

import java.text.DecimalFormat;

public class Driver {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args){

        Product product1 = new Product(1,"ToothPaste", 2.50, 20, "Toothpaste to brush your teeth", "ImageURL" );
        Product product2 = new Product(2, "Towel", 5.00, 10, "Towel for the beach", "Image");
        User user1 = new User(1,"user1", "user1@gmail.com", "password", "Leo", "Barrientos", "516-960-8000");
        System.out.println("E-COMMERCE");

        System.out.println(product1.getProductName() + " Price:" +  df.format(product1.getProductPrice())+ " Description: " + product1.getProductDescription());
        System.out.println(product2.getProductName() + " Price:" + df.format(product2.getProductPrice()) + " Description: " + product2.getProductDescription());
        System.out.println(user1);
    }
}
