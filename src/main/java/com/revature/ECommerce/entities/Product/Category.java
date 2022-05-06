package com.revature.ECommerce.entities.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category", schema = "public")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Category(){}

    public Category(Integer id, Product product, String name, String description) {
        this.id = id;
        this.product = product;
        this.name = name;
        this.description = description;
    }

    public Category(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
