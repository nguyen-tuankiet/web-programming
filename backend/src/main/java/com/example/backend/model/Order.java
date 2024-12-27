package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.Date;

public class Order {
    Integer id;
    Date createAt;
    String paymentStatus;
    String orderStatus;
    Integer userId;
    Integer cardId;
    Integer addressId;
    Boolean isCOD;


    public Order(@ColumnName("id") Integer id,
                 @ColumnName("createAt") Date createAt,
                 @ColumnName("paymentStatus") String paymentStatus,
                 @ColumnName("orderStatus") String orderStatus,
                 @ColumnName("userId") Integer userId,
                 @ColumnName("cardId") Integer cardId,
                 @ColumnName("addressId") Integer addressId,
                 @ColumnName("isCOD") Boolean isCOD) {
        this.id = id;
        this.createAt = createAt;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.cardId = cardId;
        this.addressId = addressId;
        this.isCOD = isCOD;
    }
}
