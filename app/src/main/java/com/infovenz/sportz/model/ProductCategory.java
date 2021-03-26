package com.infovenz.sportz.model;

public class ProductCategory {
    Integer productID;
    String productName;


    public ProductCategory(Integer productID, String productName) {
        this.productID = productID;
        this.productName = productName;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
