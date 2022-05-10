package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.beans.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository pRepo;
    boolean running = false;

    @Autowired
    public ProductService(ProductRepository pRepo){
        this.pRepo= pRepo;
    }

    public Product saveProduct(Product product){
        return pRepo.save(product);
    }

    public Product getProductById(Integer id){
        return pRepo.getById(id);
    }

    public List<Product> getAllProducts(){
        return pRepo.getAll();
    }

    public void deleteProduct(Product product){
        pRepo.deleteProduct(product);
    }


}
