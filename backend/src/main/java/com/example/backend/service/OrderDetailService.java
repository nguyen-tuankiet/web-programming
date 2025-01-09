package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.OrderDetailDAO;
import com.example.backend.model.OrderDetail;
import org.jdbi.v3.core.Jdbi;

public class OrderDetailService {
    Jdbi jdbi;
    OrderDetailDAO orderDetailDAO;
    public OrderDetailService(Jdbi jdbi) {
        orderDetailDAO = jdbi.onDemand(OrderDetailDAO.class);
    }


    public Boolean addOrderDetail(OrderDetail orderDetail) {
        return orderDetailDAO.addOrderDetail(
                orderDetail.getOrderId(),
                orderDetail.getProductId(),
                orderDetail.getQuantity(),
                orderDetail.getTotal(),
                orderDetail.getOptionId()
        );
    }


}
