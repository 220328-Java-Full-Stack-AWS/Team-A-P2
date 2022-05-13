package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.entities.Address;

import java.util.List;

public class AddressRepository implements HibernateRepository<Address>{


    @Override
    public Address save(Address address) {
        return address;
    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public Address getById(Integer id) {
        return null;
    }

    @Override
    public Address update(Address address) {
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
