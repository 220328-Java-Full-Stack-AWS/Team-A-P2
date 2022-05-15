package com.revature.ECommerce.beans.controllers;

import com.revature.ECommerce.beans.services.PaymentService;
import com.revature.ECommerce.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    public final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Payment> getAllPayment() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Payment getPayment(@PathVariable Integer id) {
        return paymentService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public Payment persistNewPayment(@RequestBody Payment newPayment){
        return paymentService.save(newPayment);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Payment updatePayment(@RequestBody Payment payment){
        return paymentService.updatePayment(payment);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePayment(@RequestBody Payment payment){ paymentService.delete(payment);
    }
}