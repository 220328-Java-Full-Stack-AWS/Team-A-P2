package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        return product;
    }

    @Override
    public List<Product> getAll() {
        TypedQuery<Product> query = session.createQuery("FROM Product");
        List<Product> products = query.getResultList();

        return products;
    }

    @Override
    public Product getById(Integer id) {
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE id = :product_id", Product.class);
        query.setParameter("product_id", id);
        Product product = query.getSingleResult();

        return product;
    }

    public Product getByName (String name){
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE name = :product_name", Product.class);
        query.setParameter("product_name", name);
        Product product = query.getSingleResult();

        return product;
    }

    //Returns a list of products based on a selected category.
    public List<Product> getByCategory(String category){
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE category = :category", Product.class);
        query.setParameter("category", category);
        List<Product> products = query.getResultList();

        return products;
    }

    //Returns a list of products based on availability.
    //The two status strings we are using are, In Stock and Out of Stock.
    public List<Product> getByStatus(String status){
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE status = :status", Product.class);
        query.setParameter("status", status);
        List<Product> products = query.getResultList();

        return products;
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

    public void deleteById(Integer id) {
        TypedQuery<Product> query = session.createQuery("Delete Product WHERE id = :product_id");
        query.setParameter("product_id", id);
        query.executeUpdate();
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
