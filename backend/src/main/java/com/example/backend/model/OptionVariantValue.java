package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class OptionVariantValue {
    Integer id ;
    Integer optionId;
    Integer variantValueId;

    public OptionVariantValue(@ColumnName("id") Integer id,
                              @ColumnName("optionId") Integer optionId,
                              @ColumnName("variantValueId") Integer variantValueId) {
        this.id = id;
        this.optionId = optionId;
        this.variantValueId = variantValueId;
    }
}
