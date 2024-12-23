package com.example.web_programming.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Attribute {
    int id;
    String categoryId;
    String name;

    public Attribute(@ColumnName("id") int id,
                     @ColumnName("categoryId") String categoryId,
                     @ColumnName("name") String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }
}
