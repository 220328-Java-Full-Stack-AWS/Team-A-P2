package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.PaymentRepository;
import com.revature.ECommerce.entities.Address;
import com.revature.ECommerce.entities.Payment;
import com.revature.ECommerce.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PaymentServiceTest {
    private PaymentService paymentService;
    @MockBean
    private PaymentRepository mockPaymentRepository;

    public PaymentServiceTest(@Autowired PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @Test
    void getAllPayments() {
        Payment p1 = new Payment("5555 5555 5555 4444","03/2099","234",2);
        Payment p2 = new Payment("7675 5555 5555 4444","12/2099","432",11);

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(p1);
        paymentList.add(p2);

        when(mockPaymentRepository.getAll()).thenReturn(paymentList);
        assertEquals(paymentList, paymentService.getAllPayments());
        verify(mockPaymentRepository).getAll();
    }

    @Test
    void getById() {
        Payment p1 = new Payment("5555 5555 5555 4444","03/2099","234",2);
        Mockito.when(mockPaymentRepository.getById(2)).thenReturn(p1);

        Assertions.assertEquals(p1, paymentService.getById(2));
        Mockito.verify(mockPaymentRepository).getById(2);
    }

    @Test
    void updatePayment() {
        Payment p1 = new Payment("5555 5555 5555 4444","03/2099","234",2);
        Payment p2 = new Payment("7675 5555 5555 4444","12/2099","432",11);

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(p1);
        paymentList.add(p2);

        p1 = p2;
        when(mockPaymentRepository.update(any(Payment.class))).thenReturn(p1);
        Assertions.assertEquals(p1,paymentService.updatePayment(p1));
        verify(mockPaymentRepository).update(p1);
    }

    @Test
    void delete() {
        Payment p1 = new Payment("5555 5555 5555 4444","03/2099","234",2);

        doNothing().when(mockPaymentRepository).delete(2);
        paymentService.delete(2);
        verify(mockPaymentRepository).delete(2);
    }
}