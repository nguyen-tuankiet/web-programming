package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jetbrains.annotations.Nullable;


public class Product {
    // Fields
    Integer id;
    String name;
    String sku;
    String description;
    Boolean isActive;
    Integer categoryId;
    Integer brandId;
    Integer noOfViews;
    Integer noOfSold;
    Integer primaryImage;
    Integer price;

    // Constructor
    public Product(
            @ColumnName("id") Integer id,
            @ColumnName("name") @Nullable String name,
            @ColumnName("sku") @Nullable String sku,
            @ColumnName("description") @Nullable String description,
            @ColumnName("isActive") @Nullable Boolean isActive,
            @ColumnName("categoryId") @Nullable Integer categoryId,
            @ColumnName("brandId") @Nullable Integer brandId,
            @ColumnName("noOfViews") @Nullable Integer noOfViews,
            @ColumnName("noOfSold") @Nullable Integer noOfSold,
            @ColumnName("primaryImage") @Nullable Integer primaryImage,
            @ColumnName("price") @Nullable Integer price
    ) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.isActive = isActive;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.noOfViews = noOfViews;
        this.noOfSold = noOfSold;
        this.primaryImage = primaryImage;
        this.price = price;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getNoOfViews() {
        return noOfViews;
    }

    public void setNoOfViews(Integer noOfViews) {
        this.noOfViews = noOfViews;
    }

    public Integer getNoOfSold() {
        return noOfSold;
    }

    public void setNoOfSold(Integer noOfSold) {
        this.noOfSold = noOfSold;
    }

    public Integer getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(Integer primaryImage) {
        this.primaryImage = primaryImage;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
