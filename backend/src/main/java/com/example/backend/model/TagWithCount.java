package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class TagWithCount {
    private Integer id;
    private String name;
    private Integer totalProducts;
    private Boolean isActive;

    public TagWithCount(@ColumnName("id") Integer id,
                        @ColumnName("name") String name,
                        @ColumnName("totalProducts") Integer totalProducts,
                        @ColumnName("isActive") Boolean isActive) {
        this.id = id;
        this.name = name;
        this.totalProducts = totalProducts;
        this.isActive = isActive;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Integer getTotalProducts() { return totalProducts; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTotalProducts(Integer totalProducts) { this.totalProducts = totalProducts; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
