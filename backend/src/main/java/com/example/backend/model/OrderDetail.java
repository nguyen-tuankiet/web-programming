package com.example.backend.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class OrderDetail {
    Integer id;
    Integer orderId;
    Integer productId;
    Integer quantity;
    Integer total;

    public OrderDetail(@ColumnName("id") Integer id,
                       @ColumnName("orderId") Integer orderId,
                       @ColumnName("productId") Integer productId,
                       @ColumnName("quantity") Integer quantity,
                       @ColumnName("total")Integer total) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
    }
}
