package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.ProductRepository;
import com.revature.ECommerce.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService  {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.getAll();
    }

    public Product getProductById(Integer id){return productRepository.getById(id);}

//    public Product getProductByName(String name){
//        return productRepository.getByName(name);
//    }
    public List<Product> getByCategory(String category){return productRepository.getByCategory(category);}

    public List<Product> getByStatus(String status){return productRepository.getByStatus(status);}

    public Product saveProduct(Product product){
         return  productRepository.save(product);
    }

    public void deleteProductById(Integer id){
        productRepository.deleteById(id);
    }

    public void deleteProduct(Product product){
        productRepository.deleteProduct(product);
    }

    public Product updateProduct(Product product){
        return productRepository.update(product);
    }

    public List<Product> sortPriceDesc(){ return productRepository.sortPriceDESC();}

    public List<Product> sortPriceAsc(){ return productRepository.sortPriceASC();}

    public List<Product> sortProductNameAsc(){ return productRepository.sortProductNameASC();}

    public List<Product> sortProductNameDesc(){ return productRepository.sortProductNameDESC();}
}
