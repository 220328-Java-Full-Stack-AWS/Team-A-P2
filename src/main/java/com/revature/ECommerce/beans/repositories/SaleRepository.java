package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.entities.Sale;

import java.util.List;

public class SaleRepository implements HibernateRepository<Sale>{


    @Override
    public Sale save(Sale sale) {
        return null;
    }

    @Override
    public List<Sale> getAll() {
        return null;
    }

    @Override
    public Sale getById(Integer id) {
        return null;
    }

    @Override
    public Sale update(Sale sale) {
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
