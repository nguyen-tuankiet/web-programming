package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.beans.ConstructorProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    Integer stock;
    Integer optionId;
    String categoryName;
    String imageUrl;

    Integer height;
    Integer length;
    Integer width;
    Integer weight;


    @ConstructorProperties({"id", "name", "sku", "description", "isActive", "categoryId", "brandId"
            , "noOfViews", "noOfSold", "primaryImage", "imageUrl", "price", "optionId", "stock", "categoryName"
            , "height", "length", "width", "weight"
    })
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
            @ColumnName("imageUrl") @Nullable String imageUrl,
            @ColumnName("price") @Nullable Integer price,
            @ColumnName("optionId") @Nullable Integer optionId,
            @ColumnName("stock") @Nullable Integer stock,
            @ColumnName("categoryName") @Nullable String categoryName,
            @ColumnName("height") @Nullable Integer height,
            @ColumnName("length") @Nullable Integer length,
            @ColumnName("width") @Nullable Integer width,
            @ColumnName("weight") @Nullable Integer weight

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
        this.optionId = optionId;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.categoryName = categoryName;
        this.height = height;
        this.length = length;
        this.width = width;
        this.weight = weight;
    }


    public Product(
            Integer id,
            @Nullable String name,
            @Nullable String sku,
            @Nullable String description,
            @Nullable Boolean isActive,
            @Nullable Integer categoryId,
            @Nullable Integer brandId,
            @Nullable Integer noOfViews,
            @Nullable Integer noOfSold,
            @Nullable Integer primaryImage,
            @Nullable String imageUrl,
            @Nullable Integer price,
            @Nullable Integer optionId,
            @Nullable Integer stock,
            @Nullable String categoryName

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
        this.optionId = optionId;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.categoryName = categoryName;

    }


    public Product() {
    }

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", noOfViews=" + noOfViews +
                ", noOfSold=" + noOfSold +
                ", primaryImage=" + primaryImage +
                ", price=" + price +
                ", stock=" + stock +
                ", optionId=" + optionId +
                ", categoryName='" + categoryName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", height=" + height +
                ", length=" + length +
                ", width=" + width +
                ", weight=" + weight +
                '}';
    }
}