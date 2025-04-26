package com.example.backend.model;

import java.util.List;

public class ProductDTO {
    private Integer id;
    private String name;
    private String sku;
    private String description;
    private Boolean isActive;
    private Integer categoryId;
    private Integer brandId;
    private Integer noOfViews;
    private Integer noOfSold;
    private Integer primaryImage;
    private Integer price;
    private Integer stock;
    private Integer optionId;
    private String categoryName;
    private String imageUrl;
    private List<OptionVariant> variants;  // Danh sách các variant
    Integer height;
    Integer length;
    Integer width;
    Integer weight;


    public ProductDTO(Product product, List<OptionVariant> variants) {
        this.id = product.getId();
        this.name = product.getName();
        this.sku = product.getSku();
        this.description = product.getDescription();
        this.isActive = product.getActive();
        this.categoryId = product.getCategoryId();
        this.brandId = product.getBrandId();
        this.noOfViews = product.getNoOfViews();
        this.noOfSold = product.getNoOfSold();
        this.primaryImage = product.getPrimaryImage();
        this.imageUrl = product.getImageUrl();
        this.price = product.getPrice();
        this.optionId = product.getOptionId();
        this.stock = product.getStock();
        this.categoryName = product.getCategoryName();
        this.variants = variants;
        this.height = product.getHeight();
        this.length = product.getLength();
        this.width = product.getWidth();
        this.weight = product.getWeight();
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<OptionVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<OptionVariant> variants) {
        this.variants = variants;
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
}
