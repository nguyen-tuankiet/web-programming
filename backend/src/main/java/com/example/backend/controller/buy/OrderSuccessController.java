package com.example.backend.controller.buy;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderSuccessController", value = "/success")
public class OrderSuccessController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       request.getRequestDispatcher("Checkout/OrderSuccess.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
