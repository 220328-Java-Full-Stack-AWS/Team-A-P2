package com.revature.ECommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "user_payments", schema = _SchemaName.schemaName)
public class Payment implements Serializable {

    @Id
    private Integer paymentId;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "exp_date")
    private String expDate;
    @Column(name = "cvc")
    private String cvc;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    public Payment(){}

    public Payment(String cardNumber, String expDate, String cvc, Integer paymentId){
        this.cardNumber=cardNumber;
        this.expDate=expDate;
        this.cvc=cvc;
        this.paymentId = paymentId;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", cardNumber=" + cardNumber +
                ", expDate=" + expDate +
                ", cvc=" + cvc +
                ", user=" + user +
                '}';
    }
}
