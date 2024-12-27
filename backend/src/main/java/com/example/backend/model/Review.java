package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Review {
    Integer id;
    Integer userId;
    Integer productId;
    Integer rating;
    String description;

    public Review(@ColumnName("id") Integer id,
                  @ColumnName("userId") Integer userId,
                  @ColumnName("productId") Integer productId,
                  @ColumnName("rating") Integer rating,
                  @ColumnName("description") String description) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.description = description;
    }
}
