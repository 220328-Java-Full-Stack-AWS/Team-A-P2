package com.revature.ECommerce.entities.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inventory", schema = "public")
public class Inventory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public Inventory() {}

    public Inventory( Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Inventory(int quantity) {
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
