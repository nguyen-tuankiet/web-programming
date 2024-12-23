package com.example.web_programming.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Tag {
    int id;
    String name;

    public Tag(@ColumnName("id") int id, @ColumnName("name")  String name) {
        this.id = id;
        this.name = name;
    }
}
