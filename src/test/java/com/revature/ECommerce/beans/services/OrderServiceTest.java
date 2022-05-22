package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.OrderRepository;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Sale;

import com.revature.ECommerce.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {
    //Arrange - set up tests

    private OrderService oServ;
    @MockBean
    private UserService mockUserService;
    @MockBean
    private SaleService mockSaleService;
    @MockBean
    private OrderRepository mockOrderRepo;

    public OrderServiceTest(@Autowired OrderService oServ){
        this.oServ=oServ;
    }

    @BeforeAll
    static void runBeforeTestingStarts(){
    }

    @BeforeEach
    public void preTestInitialization(){
        //oServ= new OrderService(mockOrderRepo, mockUserService, mockSaleService);
    }
    @AfterEach
    public void postTestDestruction(){
        //oServ=null;
    }

    @Test
    public void addToOrderPassesWhenSaleIsSuccessfullyAdded(){
        //Act
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        //order.setSaleList(Collections.emptyList());
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        when(mockSaleService.save(s2)).thenReturn(s2);
        when(mockOrderRepo.orderExists(order)).thenReturn(Boolean.FALSE);
        order = oServ.addToOrder(order, s2);

        //Assert
        assertFalse(order.getSaleList().isEmpty());
        assertEquals(s2, order.getSaleList().get(0));


    }

    @Test
    public void removeFromOrderPassesWhenOrderHasSalesAndGivenSaleIsRemoved(){
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        //order.setSaleList(Collections.emptyList());
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        s2.setSaleId(1);
        List<Sale> temp= new ArrayList<>();
        temp.add(s2);
        order.setSaleList(temp);
        doNothing().when(mockSaleService).delete(s2);
        try {
            order=oServ.removeFromOrder(order, s2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(order.getSaleList().isEmpty());

    }

    @Test
    public void removeFromOrderFailsWhenListOfSalesIsAlreadyEmpty(){
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        //order.setSaleList(Collections.emptyList());
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        s2.setSaleId(1);
        List<Sale> temp= new ArrayList<>();
        order.setSaleList(temp);
        doNothing().when(mockSaleService).delete(s2);

        assertThrows(Exception.class, ()->oServ.removeFromOrder(order, s2));

    }

    @Test
    public void checkoutPassesWhenOrderIsAddedToUsersListOfOrders(){
        User user = new User("shady","shady@mail.com","123", "Terrell", "Crawford", "5553427788");
        List<Order>temp= new ArrayList<>();
        List<Order>temp2=new ArrayList<>();
        user.setListOfOrders(temp2);
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        Order order2= new Order();
        List<Sale>saleList1=new ArrayList<>();
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        p1.setProductId(1);
        p2.setProductId(2);
        s2.setSaleId(1);
        sale.setSaleId(2);
        saleList1.add(s2);
        saleList1.add(sale);
        order.setOrderId(1);
        order2.setOrderId(2);
        user.setUserId(1);
        order.setSaleList(saleList1);
        order2.setSaleList(saleList1);
        temp.add(order);
        when(mockUserService.update(user)).thenReturn(null);
        when(mockOrderRepo.save(order)).thenReturn(null);
        order=oServ.checkOut(user, order);
        assertEquals(temp.get(0), user.getListOfOrders().get(0));
        assertDoesNotThrow(()->oServ.checkOut(user,order2), "Only Registered users can purchase items!");
    }

    @Test
    public void checkoutFailsWhenUserDoesNotExist(){
        User user = new User();
        List<Order>temp= new ArrayList<>();
        List<Order>temp2=new ArrayList<>();
        user.setListOfOrders(temp2);
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        Order order2= new Order();
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        List<Sale>saleList1=new ArrayList<>();
        List<Sale>saleList2=new ArrayList<>();
        saleList1.add(sale);
        saleList2.add(s2);
        s2.setSaleId(1);
        sale.setSaleId(2);
        order.setOrderId(1);
        order2.setOrderId(2);
        order.setSaleList(saleList1);
        order2.setSaleList(saleList2);
        user.setUserId(null);
        temp.add(order);
        temp.add(order2);
        when(mockUserService.update(user)).thenReturn(null);
        when(mockOrderRepo.save(order)).thenReturn(null);
        assertThrows(RuntimeException.class,()->oServ.checkOut(user,order));
        assertThat(user.getListOfOrders().isEmpty());
    }

    @Test
    public void testDelete(){
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        Order order2= new Order();
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        List<Sale>saleList1=new ArrayList<>();
        List<Sale>saleList2=new ArrayList<>();
        saleList1.add(sale);
        saleList2.add(s2);
        order.setSaleList(saleList1);
        order2.setSaleList(saleList2);
        order.setOrderId(1);
        order2.setOrderId(2);
        doNothing().when(mockSaleService).delete(any(Sale.class));
        doNothing().when(mockOrderRepo).deleteOrder(any(Order.class));
        oServ.delete(order);
        verify(mockOrderRepo).deleteOrder(order);
    }

    @Test
    public void testGetById(){
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        Order order2= new Order();
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        List<Sale>saleList1=new ArrayList<>();
        List<Sale>saleList2=new ArrayList<>();
        saleList1.add(sale);
        saleList2.add(s2);
        order.setSaleList(saleList1);
        order2.setSaleList(saleList2);
        order.setOrderId(1);
        order2.setOrderId(2);
        List<Order>user1Orders=new ArrayList<>();
        user1Orders.add(order);
        user1Orders.add(order2);
        User user=new User();
        user.setListOfOrders(user1Orders);
        when(mockOrderRepo.getById(any(Integer.class))).thenReturn(order);
        assertEquals(order, oServ.getById(1));
        verify(mockOrderRepo).getById(1);
    }

    @Test
    public void testGetByUser(){
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        Order order2= new Order();
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        List<Sale>saleList1=new ArrayList<>();
        List<Sale>saleList2=new ArrayList<>();
        saleList1.add(sale);
        saleList2.add(s2);
        order.setSaleList(saleList1);
        order2.setSaleList(saleList2);
        List<Order>user1Orders=new ArrayList<>();
        user1Orders.add(order);
        user1Orders.add(order2);
        User user=new User();
        user.setListOfOrders(user1Orders);
        when(mockOrderRepo.getByUser(any(User.class))).thenReturn(user.getListOfOrders());
        assertEquals(user1Orders, oServ.getByUser(user));
        verify(mockOrderRepo).getByUser(user);
    }
    @Test
    public void testGetAll(){
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        Order order2= new Order();
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        List<Sale>saleList1=new ArrayList<>();
        List<Sale>saleList2=new ArrayList<>();
        saleList1.add(sale);
        saleList2.add(s2);
        order.setSaleList(saleList1);
        order2.setSaleList(saleList2);
        List<Order>orderList=new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);
        when(mockOrderRepo.getAll()).thenReturn(orderList);
        assertEquals(orderList, oServ.getAll());
        verify(mockOrderRepo).getAll();
    }
    @Test
    public void testUpdate(){
        Timestamp timestamp= new Timestamp(1652735621);
        Timestamp timestamp2 = new Timestamp(1652735625);
        Order order= new Order();
        Order order2= new Order();
        Product p1 = new Product("PS5", 600.00, 100, "PlayStation 5", "ImgUrl", "In Stock", "Video Games");
        Product p2 = new Product("XBOX Series X", 600.00, 100, "It's an XBOX", "ImgUrl", "In Stock", "Video Games");
        Sale sale = new Sale(3,timestamp,p2);
        Sale s2 = new Sale(50, timestamp2, p1);
        List<Sale>saleList1=new ArrayList<>();
        List<Sale>saleList2=new ArrayList<>();
        saleList1.add(sale);
        saleList2.add(s2);
        order.setSaleList(saleList1);
        order2.setSaleList(saleList2);
        order=order2;
        when(mockOrderRepo.update(any(Order.class))).thenReturn(order);
        assertEquals(order, oServ.update(order));
        verify(mockOrderRepo).update(order);
    }

}
