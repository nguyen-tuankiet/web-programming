package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class VariantValue {
    Integer id;
    Integer variantId;
    String value;

    public VariantValue(@ColumnName("id") Integer id,
                        @ColumnName("variantId")  Integer variantId,
                        @ColumnName("value")  String value) {
        this.id = id;
        this.variantId = variantId;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}
