package com.revature.ECommerce.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products", schema = _SchemaName.schemaName)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Double productPrice;

    @Column(name = "quantity")
    private Integer productQuantity;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "image")
    private String productImage;

    @Column(name = "status")
    private String productStatus;

    @Column(name = "category")
    private String productCategory;


    /*@OneToOne(cascade = CascadeType.ALL)
    private Sale sale;*/



    public Product() {
    }

    public Product(String productName, Double productPrice, Integer productQuantity, String productDescription, String productImage, String productStatus, String productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.productStatus=productStatus;
        this.productCategory=productCategory;
    }


    /*public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }*/


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }


    public String getProductImage() {
        return productImage;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "Product:" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", productDescription='" + productDescription + '\'' +
                ", productImage='" + productImage + '\''+
                ", productStatus= '" + productStatus;
    }
}