package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.entities.User;
import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revature.ECommerce.beans.repositories.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {
    /*
    The Three A's of Testing:
    Arrange - set up the test
    Act  - preform the test
    Assert - check the results
     */

    private UserService uServ;

    @MockBean
    private UserRepository mockUserRepository;

    //@BeforeAll //apply to method which will be invoked before the suite of tests starts running - was called @BeforeClass in Junit 4
    //@AfterAll //apply to method which will be invoked after all tests have completed - was called @AfterClass in Junit 4

    //@BeforeEach //apply to method which will be invoked before EACH test - was called @Before in Junit 4
    //@AfterEach //apply to method which will be invoked after EACH test - was called @After in Junit 4

    @BeforeAll
    public static void initializeTestSuite() {

    }

    @AfterAll
    public static void tearDownTestSuite() {

    }

    @BeforeEach
    public void initializeTest() {
        uServ = new UserService(mockUserRepository);
    }

    @AfterEach
    public void tearDownTest() {
        uServ = null;
    }

    /**
     * Tests Start here
     *
     */


    /*
    @Test
    public void test_authenticateUserSuccess() {
        //arrange
        String username = "username";
        String email = "email";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String phone = "phone";
        User testUser = new User(username, email, password, firstName, lastName, phone);
        Mockito.when(mockUserRepository.auth(username, password)).thenReturn(testUser);


        //act
        User authenticatedUser = sut.auth(username, password);

        //assert
        //what can we check to prove it worked?
        //in this case we need to make sure that the "authenticatedUser" object returned from our tested method
        //matches the "testUser" object we made.
        Assertions.assertEquals(authenticatedUser, testUser);

    } */


    // GetUserByUsernameTest
    @Test
    public void test_getByUsernameSuccess() {
        //arrange
        String username = "username";
        String email = "email";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String phone = "phone";
        // User testUser = new User(username, email, password, firstName, lastName, phone);
        User testUser = new User(username, email, password, firstName, lastName, phone);

        Mockito.when(mockUserRepository.getByUsername(any(String.class))).thenReturn(testUser);

        //assert
        Assertions.assertEquals(testUser, uServ.getUserByUsername(username));
        Mockito.verify(mockUserRepository).getByUsername(username);
    }

    @Test
    public void test_getByIDSuccess() {
        //arrange
        String username = "username";
        String email = "email";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String phone = "phone";
        // User testUser = new User(username, email, password, firstName, lastName, phone);
        User testUser1 = new User(username, email, password, firstName, lastName, phone);
        User testUser2 = new User(username, email, password, firstName, lastName, phone);
        testUser1.setUserId(1);
        testUser2.setUserId(2);
        Mockito.when(mockUserRepository.getById(1)).thenReturn(testUser1);
        Mockito.when(mockUserRepository.getById(2)).thenReturn(testUser2);

        //assert
        Assertions.assertEquals(testUser1, uServ.getUserById(1));
        Assertions.assertEquals(testUser2, uServ.getUserById(2));
        Mockito.verify(mockUserRepository).getById(1);
        Mockito.verify(mockUserRepository).getById(2);
    }

    @Test
    public void test_getByIDFailure() {
        Mockito.when(mockUserRepository.getById(any(Integer.class))).thenThrow(RuntimeException.class);

        //assert
        Assertions.assertThrows(RuntimeException.class, ()->uServ.getUserById(2));
    }

    @Test
    public void test_update() {
        //arrange
        String username = "username";
        String email = "email";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String phone = "phone";
        // User testUser = new User(username, email, password, firstName, lastName, phone);
        User testUser1 = new User(username, email, password, firstName, lastName, phone);
        User testUser2 = new User(username, email, password, firstName, lastName, phone);
        testUser1.setUserId(2);
        testUser2.setUserId(2);
        Mockito.when(mockUserRepository.update(any(User.class))).thenReturn(testUser2);

        //assert
        Assertions.assertEquals(testUser2, uServ.update(testUser1));
        Mockito.verify(mockUserRepository).update(testUser1);
    }

    @Test
    public void test_delete() {

    }

    @Test
    public void test_getAllUsers() {

        User user1 = new User("georgebakhoum", "bakhoumgeorge@gmail.com", "P4ssw0rd!", "George", "Bakhoum", "832-100-1000");
        User user2 = new User("bakgeo", "bakgeo@gmail.com", "gbsw0rd!", "Geo", "Bak", "832-543-2432");
        User user3 = new User("gb", "gb@gmail.com", "simplepassword", "G", "B", "219-999-4543");



    }
    @Test
    public void test_authenticateUser() {

    }



}