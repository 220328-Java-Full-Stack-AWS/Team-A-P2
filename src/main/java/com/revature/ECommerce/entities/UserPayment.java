package com.revature.ECommerce.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_payment")
public class UserPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "payment_type", length = 100)
    private String paymentType;

    @Column(name = "provider", length = 20)
    private String provider;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "expiry", length = 250)
    private String expiry;

    public UserPayment() {
    }

    public UserPayment(String paymentType, String provider, String accountNumber, String expiry) {
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNumber = accountNumber;
        this.expiry = expiry;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

}