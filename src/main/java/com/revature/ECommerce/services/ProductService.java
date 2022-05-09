package com.revature.ECommerce.services;

import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Sales;
import com.revature.ECommerce.repositories.ProductRepository;
import org.hibernate.Session;

import java.util.List;

public class ProductService {
    private ProductRepository pRepo;
    public ProductService(ProductRepository pRepo){
        this.pRepo=pRepo;
    }

    public void saveProduct(Product product){
        pRepo.save(product);
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
