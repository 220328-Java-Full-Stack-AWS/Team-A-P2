package com.revature.ECommerce.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "discount_name", length = 250)
    private String discountName;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "active")
    private Boolean active;

    public Discount() {
    }

    public Discount(String discountName, String description, Double discountPercent, Boolean active) {
        this.discountName = discountName;
        this.description = description;
        this.discountPercent = discountPercent;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}