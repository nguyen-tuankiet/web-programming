package com.example.backend.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class OptionVariantValue {
    Integer id ;
    Integer optionId;
    Integer variantValueId;

    public OptionVariantValue(@ColumnName("id")  @Nullable Integer id,
                              @ColumnName("optionId")  @Nullable Integer optionId,
                              @ColumnName("variantValueId")  @Nullable Integer variantValueId) {
        this.id = id;
        this.optionId = optionId;
        this.variantValueId = variantValueId;
    }

    public OptionVariantValue(@Nullable Integer optionId, @Nullable Integer variantValueId) {
        this.optionId = optionId;
        this.variantValueId = variantValueId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getVariantValueId() {
        return variantValueId;
    }

    public void setVariantValueId(Integer variantValueId) {
        this.variantValueId = variantValueId;
    }
}
