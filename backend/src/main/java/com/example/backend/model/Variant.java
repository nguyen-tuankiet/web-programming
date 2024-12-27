package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Variant {
    Integer id;
    Integer categoryId;
    String name;

    public Variant(@ColumnName("id") Integer id,
                   @ColumnName("id") Integer categoryId,
                   @ColumnName("id") String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }
}
