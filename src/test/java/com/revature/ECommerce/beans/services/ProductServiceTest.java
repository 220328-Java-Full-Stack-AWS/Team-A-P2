package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.ProductRepository;

import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Product;

import com.revature.ECommerce.entities.Sale;
import com.revature.ECommerce.entities.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class ProductServiceTest {

    private ProductService pService;
    @MockBean
    private ProductRepository mockProductRepository;

    public ProductServiceTest(@Autowired ProductService pService){
        this.pService=pService;
    }

    @Test
    public void getAllProductsTest(){
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        List<Product>productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);

        when(mockProductRepository.getAll()).thenReturn(productList);
        assertEquals(productList, pService.getAllProducts());
        verify(mockProductRepository).getAll();
    }

    @Test
    public void getProductByIdTest(){
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");

        when(mockProductRepository.getById(any(Integer.class))).thenReturn(p1);
        assertEquals(p1, pService.getProductById(1));
        verify(mockProductRepository).getById(1);
    }

    @Test
    public void deleteProduct(){
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");

        doNothing().when(mockProductRepository).deleteProduct(any(Product.class));
        pService.deleteProduct(p1);
        verify(mockProductRepository).deleteProduct(p1);

    }
    @Test
    public void updateProduct(){
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");

        p1 = p2;
        when(mockProductRepository.update(any(Product.class))).thenReturn(p1);
        assertEquals(p1, pService.updateProduct(p1));
        verify(mockProductRepository).update(p1);

    }

}