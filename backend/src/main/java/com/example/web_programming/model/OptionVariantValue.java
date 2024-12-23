package com.example.web_programming.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class OptionVariantValue {
    int id ;
    int optionId;
    int variantValueId;

    public OptionVariantValue(@ColumnName("id") int id,
                              @ColumnName("optionId") int optionId,
                              @ColumnName("variantValueId") int variantValueId) {
        this.id = id;
        this.optionId = optionId;
        this.variantValueId = variantValueId;
    }
}
