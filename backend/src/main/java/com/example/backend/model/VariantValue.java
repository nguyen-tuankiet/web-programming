package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class VariantValue {
    Integer id;
    Integer variantId;
    String value;

    public VariantValue(@ColumnName("id") Integer id,
                        @ColumnName("id")  Integer variantId,
                        @ColumnName("id")  String value) {
        this.id = id;
        this.variantId = variantId;
        this.value = value;
    }
}
