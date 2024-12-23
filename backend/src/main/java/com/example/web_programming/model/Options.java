package com.example.web_programming.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Options {
    int id;
    int productId;
    int price;
    int stock;

    public Options(@ColumnName("id") int id,
                   @ColumnName("productId") int productId,
                   @ColumnName("price") int price,
                   @ColumnName("stock") int stock) {
        this.id = id;
        this.productId = productId;
        this.price = price;
        this.stock = stock;
    }
}
