package com.example.backend.controller;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.OrderStatus;
import com.example.backend.model.response.WebhookResponse;
import com.example.backend.service.OrderSerivce;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/webhook")
public class WebhookController extends HttpServlet {

    OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        WebhookResponse webhookResponse = mapper.readValue(sb.toString(), WebhookResponse.class);

        if (webhookResponse.getType().equals("Switch_status")) {
            handleSwitchStatus(webhookResponse);
        }


    }

    private void handleSwitchStatus(WebhookResponse webhookResponse) {
        String shippingId = webhookResponse.getOrderCode();
        OrderStatus status = switch (webhookResponse.getStatus()) {
            case "picked" -> OrderStatus.SHIPPED;
            case "delivered" -> OrderStatus.DELIVERED;
            case "cancel" -> OrderStatus.CANCELLED;
            case "returned" -> OrderStatus.RETURNED;
            case "delivery_fail" -> OrderStatus.FAILED;
            default -> OrderStatus.PENDING;
        };


        boolean isSuccess=  orderSerivce.updateStatusByShippingId(shippingId, status);


    }

}
