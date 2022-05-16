package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


    public void addToOrderPassesWhenSaleIsSuccessfullyAdded(){

    }

    public void removeFromOrderPassesWhenOrderHasSalesAndGivenSaleIsRemoved(){

    }

    public void removeFromOrderFailsWhenListOfSalesIsAlreadyEmpty(){

    }

    public void checkoutPassesWhenOrderIsAddedToUsersListOfOrders(){

    }

    public void checkoutFailsWhenUserDoesNotExist(){

    }

    public void testDelete(){

    }

    public void testGetById(){

    }
    public void testGetByUser(){

    }
    public void testGetAll(){

    }
    public void testUpdate(){

    }

}
