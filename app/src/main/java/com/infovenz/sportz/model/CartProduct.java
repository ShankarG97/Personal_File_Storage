package com.infovenz.sportz.model;

public class CartProduct {
    Integer productId;
    String productName;
    String productPrice;
    Integer count;
    Integer imageUrl;

    public CartProduct(Integer productId, String productName, String productPrice, Integer count, Integer imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.count = count;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductCount() {
        return count;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }
}
