package com.revature.ECommerce.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_payments", schema = "jh")
public class Payment {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @Column(name = "card_number")
    private Integer cardNumber;
    @Column(name = "exp_date")
    private Timestamp experationDate;
    @Column(name = "cvc")
    private Integer cvc;
    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    public Payment(){}

    public Payment(Integer cardNumber, Timestamp experationDate, Integer cvc){
        this.cardNumber=cardNumber;
        this.experationDate=experationDate;
        this.cvc=cvc;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getExperationDate() {
        return experationDate;
    }

    public void setExperationDate(Timestamp experationDate) {
        this.experationDate = experationDate;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }
}
