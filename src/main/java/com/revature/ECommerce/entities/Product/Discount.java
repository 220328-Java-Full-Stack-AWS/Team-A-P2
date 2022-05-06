package com.revature.ECommerce.entities.Product;

import com.revature.ECommerce.entities.Product.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "discount", schema = "public")
public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "discount_percent")
    private Double discount_percent;

    @Column(name = "active")
    private Boolean active;

    public Discount() {
    }

    public Discount(String name, String description, Double discount_percent, Boolean active) {
        this.name = name;
        this.description = description;
        this.discount_percent = discount_percent;
        this.active = active;
    }

    public Discount(Product product, String name, String description, Double discount_percent, Boolean active) {
        this.product = product;
        this.name = name;
        this.description = description;
        this.discount_percent = discount_percent;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(Double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
