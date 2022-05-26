package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.AddressRepository;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.entities.Address;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    private AddressService aServ;

    @MockBean
    private AddressRepository mockAddyRepo;

    public AddressServiceTest(@Autowired AddressService addy) {
        this.aServ = addy;
    }

    @BeforeAll
    public static void initializeTestSuite(){}
    @AfterAll
    public static void tearDownTestSuite() {}
    @BeforeEach
    void setUp() {}
    @AfterEach
    void tearDown() {}

    @Test
    void save() {
        Address address1 = new Address("testAddy1","testCity","testState","testCountry","test",2);
        Address address2 = new Address("testAddy2","testCity","testState","testCountry","test",8);

        Mockito.when(mockAddyRepo.save(address1)).thenReturn(address1);
        Mockito.when(mockAddyRepo.save(address2)).thenReturn(address2);

        Address address3 = aServ.save(address1);
        Address address4 = aServ.save(address2);

        Assertions.assertEquals(address1, address3);
        Assertions.assertEquals(address2, address4);
    }

    @Test
    void getAllAddress() {
        Address address1 = new Address("testAddy1","testCity","testState","testCountry", "test", 1);
        Address address2 = new Address("testAddy2","testCity","testState","testCountry", "test", 2);
        address1.setAddressId(1);
        address2.setAddressId(2);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);

        Mockito.when(mockAddyRepo.getAll()).thenReturn(addressList);

        Assertions.assertEquals(addressList,aServ.getAllAddress());
        Mockito.verify(mockAddyRepo).getAll();
    }

    @Test
    void getAddressById() {
        Address testAddy = new Address("testAddress","testCity","testState","testCountry","test",3);
        testAddy.setAddressId(1);
        Mockito.when(mockAddyRepo.getById(1)).thenReturn(testAddy);

        Assertions.assertEquals(testAddy, aServ.getAddressById(1));
        Mockito.verify(mockAddyRepo).getById(1);
    }

    @Test
    void updateAddress() {
        Address address1 = new Address("testAddy1","testCity","testState","testCountry","test",4);
        Address address2 = new Address("testAddy2","testCity","testState","testCountry","test",5);
        address1.setAddressId(1);
        address2.setAddressId(2);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);

        address1 = address2;
        Mockito.when(mockAddyRepo.update(any(Address.class))).thenReturn(address1);
        Assertions.assertEquals(address1,aServ.updateAddress(address1));
        Mockito.verify(mockAddyRepo).update(address1);

    }

    @Test
    void deleteAddress() {
        Address address1 = new Address("testAddy1","testCity","testState","testCountry","test",6);
        Address address2 = new Address("testAddy2","testCity","testState","testCountry","test",7);
        address1.setAddressId(1);
        address2.setAddressId(2);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);

        Mockito.doNothing().when(mockAddyRepo).delete(address1.getAddressId());
        aServ.deleteAddress(address1.getAddressId());

        Mockito.verify(mockAddyRepo).delete(address1.getAddressId());
    }

    @Test
    void getByState() {
        Address address1 = new Address("testAddy1","testCity","testState","testCountry","test",6);
        Address address2 = new Address("testAddy2","testCity","testState","testCountry","test",7);
        address1.setAddressId(1);
        address2.setAddressId(2);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);

        Mockito.when(mockAddyRepo.getByState("testState")).thenReturn(addressList);

        Assertions.assertEquals(addressList,aServ.getByState("testState"));
        Mockito.verify(mockAddyRepo).getByState("testState");
    }

    @Test
    void getByCountry() {
        Address address1 = new Address("testAddy1","testCity","testState","testCountry","test",6);
        Address address2 = new Address("testAddy2","testCity","testState","testCountry","test",7);
        address1.setAddressId(1);
        address2.setAddressId(2);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);

        Mockito.when(mockAddyRepo.getByCountry("testCountry")).thenReturn(addressList);

        Assertions.assertEquals(addressList,aServ.getByCountry("testCountry"));
        Mockito.verify(mockAddyRepo).getByCountry("testCountry");

    }

    @Test
    void getByCity() {
        Address address1 = new Address("testAddy1","testCity","testState","testCountry","test",6);
        Address address2 = new Address("testAddy2","testCity","testState","testCountry","test",7);
        address1.setAddressId(1);
        address2.setAddressId(2);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);

        Mockito.when(mockAddyRepo.getByCity("testCity")).thenReturn(addressList);

        Assertions.assertEquals(addressList,aServ.getByCity("testCity"));
        Mockito.verify(mockAddyRepo).getByCity("testCity");
    }

    @Test
    void getByZipCode() {
        Address address1 = new Address("testAddy1","testCity","testState","testCountry","test",6);
        Address address2 = new Address("testAddy2","testCity","testState","testCountry","test",7);
        address1.setAddressId(1);
        address2.setAddressId(2);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);

        Mockito.when(mockAddyRepo.getByZipCode(1)).thenReturn(addressList);

        Assertions.assertEquals(addressList,aServ.getByZipCode(1));
        Mockito.verify(mockAddyRepo).getByZipCode(1);
    }

    @Test
    public void getByUser(){
        Address address = new Address("testAddy1","testCity","testState","testCountry","test",6);
        address.setAddressId(1);
        User user = new User("username","email","password","firstname","lastname","phone");
        user.setUserId(1);
        user.setAddress(address);
        List<User> userList = new ArrayList<>();
        List<Address> addyList = new ArrayList<>();
        addyList.add(address);
        Mockito.when(mockAddyRepo.getByUserId(1)).thenReturn(addyList);
        Assertions.assertEquals(addyList,aServ.getByUser(1));
        Mockito.verify(mockAddyRepo).getByUserId(1);
    }
}