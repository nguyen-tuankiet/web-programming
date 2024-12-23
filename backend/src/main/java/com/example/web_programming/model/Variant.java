package com.example.web_programming.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Variant {
    int id;
    int categoryId;
    String name;

    public Variant(@ColumnName("id") int id,
                   @ColumnName("id") int categoryId,
                   @ColumnName("id") String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }
}
