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
                request.getRequestDispatcher("admin/addProduct.jsp").forward(request, response);
                break;
            case "dashboard":
            default:
                request.getRequestDispatcher("admin/admin.jsp").forward(request, response);
                break;
        }
    }
}