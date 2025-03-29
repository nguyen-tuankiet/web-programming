package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Tag {
    Integer id;
    String name;

    public Tag(@ColumnName("id") Integer id, @ColumnName("name")  String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
