package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.PaymentRepository;
import com.revature.ECommerce.entities.Payment;
import com.revature.ECommerce.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments(){
        return paymentRepository.getAll();
    }

    public Payment getById(Integer id){
        return paymentRepository.getById(id);
    }

    public Payment updatePayment(Payment payment){
        return paymentRepository.update(payment);
    }

    public void delete(Payment payment){
        paymentRepository.delete(payment);
    }

    public List<Payment> getAllPaymentsByUser(Integer id){
        return paymentRepository.getPaymentsByUser(id);
    }
}
