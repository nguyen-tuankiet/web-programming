package com.example.web_programming.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Review {
    int id;
    int userId;
    int productId;
    int rating;
    String description;

    public Review(@ColumnName("id") int id,
                  @ColumnName("userId") int userId,
                  @ColumnName("productId") int productId,
                  @ColumnName("rating") int rating,
                  @ColumnName("description") String description) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.description = description;
    }
}
