package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Category {
    Integer id;
    String name;

    public Category(@ColumnName("id") Integer id, @ColumnName("name")  String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id + "," +
                "\"name\": \"" + name + "\"" +
                "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
