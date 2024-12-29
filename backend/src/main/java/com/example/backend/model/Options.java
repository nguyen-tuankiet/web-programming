package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Options {
    Integer id;
    Integer productId;
    Integer price;
    Integer stock;

    public Options(@ColumnName("id") Integer id,
                   @ColumnName("productId") Integer productId,
                   @ColumnName("price") Integer price,
                   @ColumnName("stock") Integer stock) {
        this.id = id;
        this.productId = productId;
        this.price = price;
        this.stock = stock;
    }

    public Options() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
