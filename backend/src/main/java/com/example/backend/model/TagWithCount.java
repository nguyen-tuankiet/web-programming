package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TagWithCount {
    private Integer id;
    private String name;
    private Integer totalProducts;

    public TagWithCount(@ColumnName("id") Integer id,
                        @ColumnName("name") String name,
                        @ColumnName("totalProducts") Integer totalProducts) {
        this.id = id;
        this.name = name;
        this.totalProducts = totalProducts;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Integer getTotalProducts() { return totalProducts; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTotalProducts(Integer totalProducts) { this.totalProducts = totalProducts; }
}
