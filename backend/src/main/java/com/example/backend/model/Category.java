package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Category {
    Integer id;
    String name;

    public Category(@ColumnName("id") Integer id, @ColumnName("name")  String name) {
        this.id = id;
        this.name = name;
    }
}
