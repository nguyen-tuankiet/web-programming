package com.example.backend.model;


import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class Options {
    Integer id;
    Integer productId;
    Integer price;
    Integer stock;
    Integer variantId;
    String variantName;
    String variantValue;


    @JdbiConstructor
    public Options(@ColumnName("id") @Nullable Integer id,
                   @ColumnName("productId") @Nullable Integer productId,
                   @ColumnName("price") @Nullable Integer price,
                   @ColumnName("stock") @Nullable  Integer stock ,
                    @ColumnName("variantId") @Nullable Integer variantId,
                    @ColumnName("variantName") @Nullable String variantName,
                    @ColumnName("variantValue") @Nullable String variantValue


                   ){
        this.id = id;
        this.productId = productId;
        this.price = price;
        this.stock = stock;
        this.variantId = variantId;
        this.variantName = variantName;
        this.variantValue = variantValue;

    }

    public Options() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getVariantValue() {
        return variantValue;
    }

    public void setVariantValue(String variantValue) {
        this.variantValue = variantValue;
    }

    @Override
    public String toString() {
        return "Options{" +
                "id=" + id +
                ", productId=" + productId +
                ", price=" + price +
                ", stock=" + stock +
                ", variantId=" + variantId +
                ", variantName='" + variantName + '\'' +
                ", variantValue='" + variantValue + '\'' +
                '}';
    }
}
