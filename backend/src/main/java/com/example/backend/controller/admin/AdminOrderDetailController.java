package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Order;
import com.example.backend.model.OrderDetail;
import com.example.backend.service.OrderDetailService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderDetailController", value = "/admin/order-detail")
public class AdminOrderDetailController extends HttpServlet {
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        if (orderId != null) {
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(orderId);
            request.setAttribute("orderDetails", orderDetails);
        }

        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
