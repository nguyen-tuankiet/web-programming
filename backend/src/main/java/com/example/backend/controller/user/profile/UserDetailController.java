package com.example.backend.controller.user.profile;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UserDetailController", value = "/user-profile")
public class UserDetailController extends HttpServlet {
    UserService userService = new UserService(DBConnection.getJdbi());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
       String idString = (String) session.getAttribute("userId");

       User user= null;
        if (idString != null) {
           Integer userId = Integer.parseInt(idString);
           user = userService.getUserById(userId);
           request.setAttribute("user", user);
       }

        request.getRequestDispatcher("user/user-profile.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
