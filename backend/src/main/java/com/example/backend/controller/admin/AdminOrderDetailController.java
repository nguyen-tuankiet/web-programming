package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Order;
import com.example.backend.model.OrderDetail;
import com.example.backend.model.User;
import com.example.backend.service.OrderDetailService;
import com.example.backend.service.OrderSerivce;
import com.example.backend.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderDetailController", value = "/admin/order-detail")
public class AdminOrderDetailController extends HttpServlet {
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
    OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());
    UserService userService = new UserService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderSerivce.getOrderById(orderId);



        if (order.getId() != null) {
            request.setAttribute("order", order);

            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(order.getId());
            request.setAttribute("orderDetails", orderDetails);

            User user = userService.getUserById(order.getUserId());
            request.setAttribute("user", user);


        }

        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
