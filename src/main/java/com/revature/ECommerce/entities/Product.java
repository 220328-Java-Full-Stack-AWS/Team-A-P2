package com.revature.ECommerce.entities;

import javax.persistence.*;

@Entity
@Table(name = "products", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private Double productPrice;
    @Column(name = "product_seller")
    private String productSeller; //not sure if this one is needed...
    // Are we doing like Amazon or something or like a company specific store so the seller is obvious?
    @Column(name = "product_descritption")
    private String productDescription;
    @Column(name = "product_image")
    private String productImage;
    @Column(name="product_category")
    private String productCategory;

// Product Seller/Category/Price will allow us to have 3 types of filters I am hoping

    public Product() {
    }

    public Product(Integer productId, String productName, Double productPrice, Integer productQuantity, String productDescription, String productImage, String productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSeller = productSeller;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.productCategory=productCategory;
    }

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

    public String getProductSeller() {
        return productSeller;
    }

    public void setProductSeller(String productSeller) {
        this.productSeller = productSeller;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productStatus) {
        this.productCategory = productStatus;
    }

    @Override
    public String toString() {
        return "Product:" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productSeller=" + productSeller +
                ", productDescription='" + productDescription + '\'' +
                ", productImage='" + productImage + '\''+
                ", productCategory= '" + productCategory;
    }
}
