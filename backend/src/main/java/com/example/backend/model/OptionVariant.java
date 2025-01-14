package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class OptionVariant {
    private Integer variantId;
    private Integer variantValueId;
    private String variantName;
    private String variantValueName;

    public OptionVariant(@ColumnName("variantId") Integer variantId,
                         @ColumnName("variantValueId") Integer variantValueId,
                         @ColumnName("variantName") String variantName,
                         @ColumnName("variantValueName") String variantValueName) {
        this.variantId = variantId;
        this.variantValueId = variantValueId;
        this.variantName = variantName;
        this.variantValueName = variantValueName;
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public Integer getVariantValueId() {
        return variantValueId;
    }

    public void setVariantValueId(Integer variantValueId) {
        this.variantValueId = variantValueId;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getVariantValueName() {
        return variantValueName;
    }

    public void setVariantValueName(String variantValueName) {
        this.variantValueName = variantValueName;
    }
    // Getters and Setters
}
