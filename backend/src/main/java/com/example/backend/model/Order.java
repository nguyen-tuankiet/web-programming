package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.Date;

public class Order {
    int id;
    Date createAt;
    String paymentStatus;
    String orderStatus;
    int userId;
    int cardId;
    int addressId;
    boolean isCOD;


    public Order(@ColumnName("id") int id,
                 @ColumnName("createAt") Date createAt,
                 @ColumnName("paymentStatus") String paymentStatus,
                 @ColumnName("orderStatus") String orderStatus,
                 @ColumnName("userId") int userId,
                 @ColumnName("cardId") int cardId,
                 @ColumnName("addressId") int addressId,
                 @ColumnName("isCOD") boolean isCOD) {
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
