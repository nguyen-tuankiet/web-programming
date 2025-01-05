package com.example.backend.model.DAO.cart;

import com.example.backend.model.Product;

public class ProductCart {
    Integer productId;
    Integer optionId;
    String name;
    String imageUrl;
    Integer quantity;
    Integer price;
    Integer stock;


    public ProductCart(Product product) {
        this.productId = product.getId();
        this.optionId = product.getOptionId();
        this.name = product.getName();
        this.imageUrl = product.getImageUrl();
        this.quantity = 1;
        this.price = product.getPrice();
        this.stock = product.getStock();

    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageURL(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
