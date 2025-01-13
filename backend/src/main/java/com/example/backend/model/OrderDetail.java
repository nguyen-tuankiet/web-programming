package com.example.backend.model;

import jakarta.annotation.Nullable;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class OrderDetail {
    Integer id;
    Integer orderId;
    Integer productId;
    Integer quantity;
    Integer total;
    Integer optionId;
    String productName;

    @JdbiConstructor
    public OrderDetail(@ColumnName("id") @Nullable Integer id,
                       @ColumnName("orderId") @Nullable Integer orderId,
                       @ColumnName("productId") @Nullable Integer productId,
                       @ColumnName("quantity") @Nullable Integer quantity,
                       @ColumnName("total") @Nullable Integer total,
                       @ColumnName("optionId") @Nullable Integer optionId,
                       @ColumnName("productName") @Nullable String productName
    ) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
        this.optionId = optionId;
        this.productName = productName;
    }


    public OrderDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
}