package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Variant {
    Integer id;
    Integer categoryId;
    String name;

    public Variant(@ColumnName("id") Integer id,
                   @ColumnName("categoryId") Integer categoryId,
                   @ColumnName("name") String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
