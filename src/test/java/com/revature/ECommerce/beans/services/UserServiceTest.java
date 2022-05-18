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
        User testUser = new User(username, email, password, firstName, lastName, phone);
        Mockito.when(mockUserRepository.getByUsername(username)).thenReturn(testUser);


        //act
        //User authenticatedUser = uServ.auth(username, password);

        //assert
        //what can we check to prove it worked?
        //in this case we need to make sure that the "authenticatedUser" object returned from our tested method
        //matches the "testUser" object we made.
        //Assertions.assertEquals(authenticatedUser, testUser);

    }

}