package com.example.backend.controller.CartController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "IncreaseQuantity", value = "/update-quantity")
public class IncreaseQuantity extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
