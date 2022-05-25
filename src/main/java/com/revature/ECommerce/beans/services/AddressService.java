package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.AddressRepository;
import com.revature.ECommerce.entities.Address;
import com.revature.ECommerce.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public Address save(Address address){
        return addressRepository.save(address);
    }

    public List<Address> getAllAddress() {
        return addressRepository.getAll();
    }

    public Address getAddressById(Integer id){
        return addressRepository.getById(id);
    }

    public Address updateAddress(Address address){
        return addressRepository.update(address);
    }

    public void deleteAddress(Integer id){
        addressRepository.delete(id);
    }

    public List<Address> getByState(String state){
        return addressRepository.getByState(state);
    }

    public List<Address> getByCountry(String country){
        return addressRepository.getByCountry(country);
    }

    public List<Address> getByCity(String city){
        return addressRepository.getByCity(city);
    }

    public List<Address> getByZipCode(Integer zipCode){
        return addressRepository.getByZipCode(zipCode);
    }

    public List<Address> getByUser(Integer id){
        return addressRepository.getByUserId(id);
    }
}
