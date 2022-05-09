package com.revature.ECommerce.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales", schema = "public")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesId;
    @Column(name = "sales_buyer")
    private String salesBuyer;
    @Column(name = "sales_quantity")
    private Integer salesQuantity;
    @Column(name = "sales_product")
    private String salesProduct;
    @Column(name = "sales_total")
    private Double salesTotal;



    public Sales() {
    }

    public Sales(Integer salesId, String salesBuyer, Integer salesQuantity, String salesProduct, Double salesTotal) {
        this.salesId = salesId;
        this.salesBuyer = salesBuyer;
        this.salesQuantity = salesQuantity;
        this.salesProduct = salesProduct;
        this.salesTotal = salesTotal;
    }

    @Override
    public String toString() {
        return "Sale:" +
                "salesId=" + salesId +
                ", Buyer='" + salesBuyer + '\'' +
                ", Quantity=" + salesQuantity +
                ", Product=" + salesProduct +
                ", Total='" + salesTotal;
    }
}
