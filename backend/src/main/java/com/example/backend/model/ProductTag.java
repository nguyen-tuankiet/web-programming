package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class ProductTag {
    int productId;
    int tagId;

    public ProductTag(@ColumnName("productId") int productId,@ColumnName("tagId") int tagId) {
        this.productId = productId;
        this.tagId = tagId;
    }
}
