package com.revature.ECommerce.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String addy; //doing single String address bc I really don't want to deal with City,State,Zip...

    @Column
    @OneToMany
    private List<Sales> salesList;

    public User() {
    }

    public User(Integer userId, String username, String email, String password, String firstName, String lastName, String addy) {
        this.salesList=new ArrayList<>();
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addy = addy;
    }
    // allow users to update password, email address
    // allow users to update addy
    // or it's not a said stretch goal but make separate DB for Address and allow users to update,remove & have multiple saved that are just a One2Many relation

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddy() {
        return addy;
    }

    public void setAddy(String phone) {
        this.addy = phone;
    }


    public void addPurchase(Sales sales){
        this.salesList.add(sales);
    }

    public void deletePurchase(Sales sales){
        this.salesList.remove(sales);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(addy, user.addy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, password, firstName, lastName, addy);
    }

    @Override
    public String toString() {
        return "User:" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addy='" + addy;
    }
}
