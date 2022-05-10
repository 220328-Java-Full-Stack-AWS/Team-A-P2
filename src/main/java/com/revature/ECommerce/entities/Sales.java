package com.revature.ECommerce.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "product_sales", schema = "tc")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesID;
    @ManyToOne
    @JoinColumn(name = "purchaser_id")
    private User purchaser;
    @Column(name = "purchase_date")
    private Timestamp purchaseDate;

    @Column(name = "purchase_amount")
    private Double amount;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sales")
    private List<Product> productsList;


    public Sales(){
    }

    public Sales(User purchaser, Timestamp purchaseDate, List<Product> productsList, Double amount){
        this.purchaser=purchaser;
        this.purchaseDate=purchaseDate;
        this.productsList=productsList;
        this.amount=amount;
    }

    public Integer getSalesID() {
        return salesID;
    }

    public void setSalesID(Integer salesID) {
        this.salesID = salesID;
    }

    public User getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }
}
