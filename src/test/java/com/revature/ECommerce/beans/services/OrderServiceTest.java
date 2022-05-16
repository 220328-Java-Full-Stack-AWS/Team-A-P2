package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.OrderRepository;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Sale;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    //Arrange - set up tests
    private OrderService oServ;
    @Mock
    private UserService mockUserService;
    @Mock
    private SaleService mockSaleService;
    @Mock
    private OrderRepository mockOrderRepo;

    @BeforeAll
    public void runBeforeTestingStarts(){
    }

    @BeforeEach
    public void preTestInitialization(){
        oServ= new OrderService(mockOrderRepo, mockUserService, mockSaleService);
    }
    @AfterEach
    public void postTestDestruction(){
        oServ=null;
    }


    @Test
    public void addToOrderPassesWhenSaleIsSuccessfullyAdded(){
        //Act
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        order = oServ.addToOrder(order, s2);
        when(mockOrderRepo.orderExists(order)).thenReturn(Boolean.FALSE);
        //Assert
        assertFalse(order.getSaleList().isEmpty());
        assertEquals(s2, order.getSaleList().get(0));


    }

    @Test
    public void removeFromOrderPassesWhenOrderHasSalesAndGivenSaleIsRemoved(){

    }

    @Test
    public void removeFromOrderFailsWhenListOfSalesIsAlreadyEmpty(){

    }

    @Test
    public void checkoutPassesWhenOrderIsAddedToUsersListOfOrders(){

    }

    @Test
    public void checkoutFailsWhenUserDoesNotExist(){

    }

    @Test
    public void testDelete(){

    }

    @Test
    public void testGetById(){

    }

    @Test
    public void testGetByUser(){

    }
    @Test
    public void testGetAll(){

    }
    @Test
    public void testUpdate(){

    }

}
