package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Order;
import com.example.backend.service.OrderSerivce;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrdersController", value = "/admin/orders")
public class AdminOrdersController extends HttpServlet {

    OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> orders = orderSerivce.getAllOrders();

        request.setAttribute("orders", orders);

        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
