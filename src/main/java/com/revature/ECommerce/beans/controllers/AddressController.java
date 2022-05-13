package com.revature.ECommerce.beans.controllers;

import com.revature.ECommerce.beans.services.AddressService;
import com.revature.ECommerce.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    public final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Address getAddress(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public Address persistNewAddress(@RequestBody Address newAddress){
        return addressService.save(newAddress);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Address updateAddress(@RequestBody Address address){
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddress(@RequestBody Address address){
        addressService.deleteAddress(address);
    }

}
