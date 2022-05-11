package com.revature.ECommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_amount")
    private Double amount;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "city", length = 250)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "zip", length = 50)
    private String zip;

    @Column(name = "country", length = 250)
    private String country;

    @Column(name = "Carrier")
    private String shipmentCarrier;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "shipped")
    private Boolean shipped;

    public Order() {
    }

    public Order(Double amount, Timestamp orderDate, String address, String city, String state, String zip, String country, String shipmentCarrier, String trackingNumber, Boolean shipped) {
        this.amount = amount;
        this.orderDate = orderDate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.shipmentCarrier = shipmentCarrier;
        this.trackingNumber = trackingNumber;
        this.shipped = shipped;
    }
    public Order(Double amount, Timestamp orderDate, String address, String city, String state, String zip, String country) {
        this.amount = amount;
        this.orderDate = orderDate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.shipmentCarrier = "none";
        this.trackingNumber = "none";
        this.shipped = false;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShipmentCarrier() {
        return shipmentCarrier;
    }

    public void setShipmentCarrier(String shipmentCarrier) {
        this.shipmentCarrier = shipmentCarrier;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }
}
