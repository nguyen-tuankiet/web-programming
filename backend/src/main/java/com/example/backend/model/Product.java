package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jetbrains.annotations.Nullable;

public class Product {
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



    public Product(
            @ColumnName("id") Integer id,
            @ColumnName("name")@Nullable  String name,
            @ColumnName("sku") @Nullable String sku,
            @ColumnName("description") @Nullable String description,
            @ColumnName("isActive")@Nullable  Boolean isActive,
            @ColumnName("categoryId") @Nullable Integer categoryId,
            @ColumnName("brandId")@Nullable Integer brandId,
            @ColumnName("noOfViews") @Nullable Integer noOfViews,
            @ColumnName("noOfSold") @Nullable Integer noOfSold,
            @ColumnName("primaryImage")@Nullable Integer primaryImage,
            @ColumnName("price")@Nullable Integer price
    )
    {
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


}
