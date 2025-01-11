package com.example.backend.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AdminController", value = "/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "dashboard";
        }

        switch (action) {
            case "addProduct":
                response.sendRedirect(request.getContextPath() + "/admin/products");
                break;
            case "account_settings":
                response.sendRedirect(request.getContextPath() + "/admin/account_settings");
                break;
            case "list_products":
                response.sendRedirect(request.getContextPath() + "/admin/list_products");
                break;
            case "product_details":
                request.getRequestDispatcher("admin/Product-detail-admin.jsp").forward(request, response);
                break;
            case "my_profile":
                request.getRequestDispatcher("admin/MyProfile.jsp").forward(request, response);
                break;
            case "orders":
                request.getRequestDispatcher("admin/orders.jsp").forward(request, response);
                break;
            case "customers":
                request.getRequestDispatcher("customers.jsp").forward(request, response);
                break;

            case "dashboard1":
                request.getRequestDispatcher("admin/Dashboard.jsp").forward(request, response);
                break;


            case "dashboard":
//

            default:
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                break;
        }
    }
}
