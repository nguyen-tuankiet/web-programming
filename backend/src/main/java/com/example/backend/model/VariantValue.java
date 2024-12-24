package com.example.backend.model;


import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class VariantValue {
    int id;
    int variantId;
    String value;

    public VariantValue(@ColumnName("id") int id,
                        @ColumnName("id")  int variantId,
                        @ColumnName("id")  String value) {
        this.id = id;
        this.variantId = variantId;
        this.value = value;
    }
}
