package com.example.web_programming.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Category {
    int id;
    String name;

    public Category(@ColumnName("id") int id, @ColumnName("name")  String name) {
        this.id = id;
        this.name = name;
    }
}
