package com.revature.ECommerce.entities.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payments", schema = "public")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "account_num")
    private String accountNumber;

    @Column(name = "expiration")
    private String expiration;

    public Payment(){
    }

    public Payment(String paymentType, String accountNumber, String expiration) {
        this.paymentType = paymentType;
        this.accountNumber = accountNumber;
        this.expiration = expiration;
    }

    public Payment(User user, String paymentType, String accountNumber, String expiration) {
        this.user = user;
        this.paymentType = paymentType;
        this.accountNumber = accountNumber;
        this.expiration = expiration;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
