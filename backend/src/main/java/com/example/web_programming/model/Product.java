package com.example.web_programming.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Product {
    int id;
    String name;
    String sku;
    String description;
    boolean is_active;
    int categoryId;
    int brandId;
    int noOfViews;
    int noOfSold;
    int primaryImage;


    public Product(@ColumnName("id") int id,
                   @ColumnName("name") String name,
                   @ColumnName("sku") String sku,
                   @ColumnName("description") String description,
                   @ColumnName("is_active") boolean is_active,
                   @ColumnName("categoryId") int categoryId,
                   @ColumnName("brandId") int brandId,
                   @ColumnName("noOfViews") int noOfViews,
                   @ColumnName("noOfSold") int noOfSold,
                   @ColumnName("primaryImage") int primaryImage ){


        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.is_active = is_active;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.noOfViews = noOfViews;
        this.noOfSold = noOfSold;
        this.primaryImage = primaryImage;
    }























    public int getId() {
        return id;
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

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getNoOfViews() {
        return noOfViews;
    }

    public void setNoOfViews(int noOfViews) {
        this.noOfViews = noOfViews;
    }

    public int getNoOfSold() {
        return noOfSold;
    }

    public void setNoOfSold(int noOfSold) {
        this.noOfSold = noOfSold;
    }

    public int getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(int primaryImage) {
        this.primaryImage = primaryImage;
    }
}
