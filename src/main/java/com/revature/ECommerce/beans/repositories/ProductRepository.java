package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Product;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductRepository implements HibernateRepository<Product> {
    private HibernateManager hibernateManager;
    boolean running = false;
    private Session session;

    @Autowired
    public ProductRepository(HibernateManager hibernateManager){
        this.hibernateManager=hibernateManager;
    }
    @Override
    public Product save(Product product) {
        return product;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(Integer id) {
        return null;
    }

    @Override
    public Product update(Product product) {
        Product updateProduct = this.getById(product.getProductId());
        updateProduct.setProductName(product.getProductName());
        updateProduct.setProductPrice(product.getProductPrice());
        updateProduct.setProductQuantity(product.getProductQuantity());
        updateProduct.setProductDescription(product.getProductDescription());
        updateProduct.setProductImage(product.getProductImage());
        updateProduct.setProductStatus(product.getProductStatus());
        updateProduct.setProductCategory(product.getProductCategory());
        this.save(updateProduct);
        return updateProduct;
    }

    @Override
    public void start() {
        this.session=hibernateManager.getSession();
        running=true;
    }

    @Override
    public void stop() {
        running=false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
