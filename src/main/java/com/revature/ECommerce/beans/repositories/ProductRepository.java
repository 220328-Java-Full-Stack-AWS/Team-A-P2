package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.entities.Product;
import java.util.List;

public class ProductRepository implements HibernateRepository<Product> {
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product update(Product product){
        return null;
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
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
