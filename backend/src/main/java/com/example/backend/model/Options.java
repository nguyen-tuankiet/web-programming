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
}
