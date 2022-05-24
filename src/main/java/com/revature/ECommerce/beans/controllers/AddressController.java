package com.revature.ECommerce.beans.controllers;

import com.revature.ECommerce.beans.services.AddressService;
import com.revature.ECommerce.entities.Address;
import com.revature.ECommerce.entities.User;
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

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Address getAddress(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Address persistNewAddress(@RequestBody Address newAddress){
        return addressService.save(newAddress);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Address updateAddress(@RequestBody Address address){
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddress(@PathVariable Integer id){
        addressService.deleteAddress(id);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Address> getAddressesByUserId(@PathVariable Integer userId){
        return addressService.getByUser(userId);
    }
}
