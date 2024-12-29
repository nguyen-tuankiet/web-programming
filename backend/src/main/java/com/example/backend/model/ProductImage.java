package com.example.backend.model;

public class ProductImage {
    private int id;
    private int productId;
    private int imageId;

    public ProductImage() {}

    public ProductImage(int id, int productId, int imageId) {
        this.id = id;
        this.productId = productId;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", productId=" + productId +
                ", imageId=" + imageId +
                '}';
    }
}
