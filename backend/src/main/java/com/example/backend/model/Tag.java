package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Tag {
    Integer id;
    String name;
    Boolean isActive;

    public Tag(@ColumnName("id") Integer id, @ColumnName("name")  String name, @ColumnName("isActive") Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
