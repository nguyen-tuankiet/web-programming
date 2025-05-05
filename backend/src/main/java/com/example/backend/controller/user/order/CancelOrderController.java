package com.example.backend.controller.user.order;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.OrderStatus;
import com.example.backend.model.Order;
import com.example.backend.service.OrderSerivce;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CancelOrderController", value = "/cancel-order")

public class CancelOrderController extends HttpServlet {
    OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer userId = (Integer) session.getAttribute("userId");
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));

        if (userId == null) {
            throw new ServletException("User not found");
        }

        Order order = orderSerivce.getOrderById(orderId);
        if (order == null || order.getUserId() != userId) {
            throw new ServletException("Order not found");
        }

        orderSerivce.updateStatus(orderId, OrderStatus.CANCELLED);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("{\"success\": true, \"message\": \"Order cancelled successfully\"}");

    }
}
