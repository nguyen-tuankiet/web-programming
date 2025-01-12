package com.example.backend.controller.user.card;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserCardController", value = "/user-card")
public class UserCardController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        //TODO: Get cards by userID

        request.getRequestDispatcher("user/user-card.jsp").forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
