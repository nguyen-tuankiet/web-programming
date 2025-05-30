package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class ProductTag {
    Integer productId;
    Integer tagId;

    public ProductTag(@ColumnName("productId") Integer productId,@ColumnName("tagId") Integer tagId) {
        this.productId = productId;
        this.tagId = tagId;
    }

}
