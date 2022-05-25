package com.revature.ECommerce.beans.controllers;

import com.revature.ECommerce.beans.services.AddressService;
import com.revature.ECommerce.beans.services.UserService;
import com.revature.ECommerce.entities.Address;
import com.revature.ECommerce.entities.Payment;
import com.revature.ECommerce.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    public final AddressService addressService;
    public final UserService userService;

    @Autowired
    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
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

    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Address persistNewAddress(@RequestBody Address newAddress, @PathVariable Integer id) {
        User user = userService.getUserById(id);
        newAddress.setUser(user);
        addressService.save(newAddress);
        user.setAddress(newAddress);
        return newAddress;
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
