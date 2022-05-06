package com.revature.ECommerce.entities.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "products", schema = "public")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Category> category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Inventory> inventory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Discount> discount;

    public Product() {
    }

    public Product(String name, String description, Double price, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.inventory = new LinkedList<>();
        this.category = new LinkedList<>();
        this.discount = new LinkedList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addCategory(Category category){
        this.category.add(category);
    }

    public void removeCategory(Category category){
        this.category.remove(category);
    }

    public void addInventory(Inventory inventory){
        this.inventory.add(inventory);
    }

    public void removeInventory(Inventory inventory){
        this.inventory.remove(inventory);
    }

    public void addDiscount(Discount discount){
        this.discount.add(discount);
    }

    public void removeDiscount(Discount discount){
        this.discount.remove(discount);
    }


}

