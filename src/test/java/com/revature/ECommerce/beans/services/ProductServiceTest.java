package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.ProductRepository;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Sale;
import com.revature.ECommerce.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    @Autowired
    private ProductService pService;
    @MockBean
    private ProductRepository mockProductRepository;

    public ProductServiceTest(@Autowired ProductService pService){
        this.pService=pService;
    }

    @Test
    public void getAllProductsTest(){
    }
    @Test
    public void getProductByIdTest(){
    }

    @Test
    public void getProductByCategoryTest(){
    }
    @Test
    public void getProductByStatus(){

    }
    @Test
    public void saveProductTest(){

    }
    @Test
    public void deleteProductByIdTest(){
    }
    @Test
    public void deleteProduct(){

    }
    @Test
    public void updateProduct(){

    }

}