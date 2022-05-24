package com.revature.ECommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.ECommerce.entities.SchemaName.EntitySchemaName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address", schema = _SchemaName.stanSchema)
public class Address implements Serializable {

    @Id
    private Integer addressId;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip")
    private Integer zipCode;
    @Column(name = "country")
    private String country;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    public Address(){
    }

    public Address(String address, String city, String state, Integer zipCode, String country){
        this.address=address;
        this.city=city;
        this.state=state;
        this.zipCode=zipCode;
        this.country=country;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
