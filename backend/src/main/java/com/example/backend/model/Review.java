package com.example.backend.model;


import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class Review {
    Integer id;
    Integer userId;
    Integer productId;
    Integer rating;
    String description;
    Integer orderId;


    @JdbiConstructor
    public Review(@ColumnName("id") @Nullable Integer id,
                  @ColumnName("userId") @Nullable Integer userId,
                  @ColumnName("productId") @Nullable Integer productId,
                  @ColumnName("rating")@Nullable Integer rating,
                  @ColumnName("description") @Nullable String description,
                  @ColumnName("orderId") @Nullable Integer orderId)
    {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.description = description;
        this.orderId = orderId;
    }


    public Review() {
     }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
