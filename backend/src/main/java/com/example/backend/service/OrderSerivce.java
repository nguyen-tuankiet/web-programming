package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.OrderDAO;
import com.example.backend.model.Order;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDate;

public class OrderSerivce {

    OrderDAO orderDAO;


    public OrderSerivce(Jdbi jdbi) {
        orderDAO = jdbi.onDemand(OrderDAO.class);
    }

    public Integer addOrder(Order order) {
        return orderDAO.createOrder(
                order.getCreateAt(),
                order.getPaymentStatus(),
                order.getOrderStatus(),
                order.getUserId(),
                order.getAddressId(),
                order.getCardId(),
                order.getIsCOD()
        );
    }

    public static void main(String[] args) {
        OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());
        Order order = new Order();
        order.setCreateAt(LocalDate.now());
        order.setPaymentStatus("PAID");
        order.setOrderStatus("PAID");
        order.setUserId(1);
        order.setAddressId(1);
        order.setCardId(1);
        order.setIsCOD(false);


        Integer orderId = orderSerivce.addOrder(order);
        System.out.println(orderId);

    }
}
