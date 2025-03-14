package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.util.List;


public class Variant {
    Integer id;
    Integer categoryId;
    String name;
    List<VariantValue> variantValues;

    @JdbiConstructor
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

    public List<VariantValue> getVariantValues() {
        return variantValues;
    }

    public void setVariantValues(List<VariantValue> variantValues) {
        this.variantValues = variantValues;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", variantValues=" + variantValues +
                '}';
    }
}
