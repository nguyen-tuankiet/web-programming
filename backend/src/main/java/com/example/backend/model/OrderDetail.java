package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class OrderDetail {
    int id;
    int orderId;
    int productId;
    int quantity;
    int total;

    public OrderDetail(@ColumnName("id") int id,
                       @ColumnName("orderId") int orderId,
                       @ColumnName("productId") int productId,
                       @ColumnName("quantity") int quantity,
                       @ColumnName("total")int total) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
    }
}
