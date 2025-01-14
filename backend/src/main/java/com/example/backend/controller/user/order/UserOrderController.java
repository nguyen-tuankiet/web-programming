package com.example.backend.controller.user.order;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Order;
import com.example.backend.model.OrderDetail;
import com.example.backend.service.OrderDetailService;
import com.example.backend.service.OrderSerivce;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserOrderController", value = "/user-order")
public class UserOrderController extends HttpServlet {
    OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        List<Order> orders = new ArrayList<>();
        try {

            orders = orderSerivce.getOrdersByUserId(userId);
            request.setAttribute("orders", orders);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("user/user-order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
