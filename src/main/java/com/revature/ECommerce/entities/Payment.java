package com.revature.ECommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.ECommerce.entities.SchemaName.EntitySchemaName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "user_payments", schema = _SchemaName.schemaName)
public class Payment implements Serializable {

    @Id
    private Integer paymentId;
    @Column(name = "card_number")
    private Integer cardNumber;
    @Column(name = "exp_date")
    private Timestamp expDate;
    @Column(name = "cvc")
    private Integer cvc;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    public Payment(){}

    public Payment(Integer cardNumber, Timestamp experationDate, Integer cvc, Integer paymentId){
        this.cardNumber=cardNumber;
        this.expDate=experationDate;
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

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getExpDate() {
        return expDate;
    }

    public void setExpDate(Timestamp experationDate) {
        this.expDate = experationDate;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
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
