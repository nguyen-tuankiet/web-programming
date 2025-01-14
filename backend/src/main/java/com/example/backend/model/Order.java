package com.example.backend.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.time.LocalDate;
import java.util.Date;

public class Order {
    Integer id;
    LocalDate createAt;
    String paymentStatus;
    String orderStatus;
    Integer userId;
    Integer cardId;
    Integer addressId;
    Boolean isCOD;


    // Su dung cho đại diẹn order
    Integer quantity;
    Integer total;
    String productName;
    String productImage;


    @JdbiConstructor
    public Order(@ColumnName("id") @Nullable Integer id,
                 @ColumnName("createAt") @Nullable LocalDate createAt,
                 @ColumnName("paymentStatus") @Nullable String paymentStatus,
                 @ColumnName("orderStatus") @Nullable String orderStatus,
                 @ColumnName("userId") @Nullable Integer userId,
                 @ColumnName("cardId") @Nullable Integer cardId,
                 @ColumnName("addressId") @Nullable Integer addressId,
                 @ColumnName("isCOD") @Nullable Boolean isCOD,

                 @ColumnName("quantity") @Nullable Integer quantity,
                 @ColumnName("total") @Nullable Integer total,
                 @ColumnName("productName") @Nullable String productName,
                 @ColumnName("productImage") @Nullable String productImage

    ){
        this.id = id;
        this.createAt = createAt;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.cardId = cardId;
        this.addressId = addressId;
        this.isCOD = isCOD;
        this.quantity = quantity;
        this.total = total;
        this.productName = productName;
        this.productImage = productImage;
    }




    public Order( ) {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Boolean getIsCOD() {
        return isCOD;
    }

    public void setIsCOD(Boolean COD) {
        isCOD = COD;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", userId=" + userId +
                ", cardId=" + cardId +
                ", addressId=" + addressId +
                ", isCOD=" + isCOD +
                ", quantity=" + quantity +
                ", total=" + total +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                '}';
    }
}
