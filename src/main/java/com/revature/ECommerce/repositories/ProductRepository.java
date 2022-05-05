package com.revature.ECommerce.repositories;

import com.revature.ECommerce.entities.Product;

import java.util.List;

public class ProductRepository implements HibernateRepository<Product> {
    @Override
    public void save(Product product) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(Integer id) {
        return null;
    }
}
