package com.example.backend.controller.user.order;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.OrderDetail;import com.example.backend.service.OrderDetailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserOrderController", value = "/user-order")
public class UserOrderController extends HttpServlet {
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try{
            orderDetails = orderDetailService.getOrderDetailsByOrderId(1);
        }
        catch (Exception e){
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
