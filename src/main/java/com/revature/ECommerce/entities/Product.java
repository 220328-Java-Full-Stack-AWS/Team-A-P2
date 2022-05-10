package com.revature.ECommerce.entities;

import javax.persistence.*;


@Entity
@Table(name = "products", schema = "tc")
@SecondaryTable(name = "product_status", schema = "tc")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private Double productPrice;
    @Column(name = "product_quantity")
    private Integer productQuantity;
    @Column(name = "product_descritption")
    private String productDescription;
    @Column(name = "product_image")
    private String productImage;
    @Column(name = "status", table = "product_status")
    private String productStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "products_in_sale", schema = "tc", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "productid"),inverseJoinColumns = @JoinColumn(name = "sale_id", referencedColumnName = "salesid") )
    private Sales sales;

    @Transient
    int quantitySold;

    public Product() {
    }

    public Product(String productName, Double productPrice, Integer productQuantity, String productDescription, String productImage, String productStatus) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.productStatus=productStatus;
        this.quantitySold=0;
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

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getProductImage() {
        return productImage;
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
