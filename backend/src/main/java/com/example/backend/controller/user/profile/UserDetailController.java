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
        
        // Debug session attributes
        System.out.println("Session ID: " + session.getId());
        System.out.println("IsLoggedIn: " + session.getAttribute("isLoggedIn"));
        System.out.println("UserRole: " + session.getAttribute("userRole"));
        System.out.println("UserId from session: " + session.getAttribute("userId"));

        // Kiểm tra đăng nhập
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null || !isLoggedIn) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Lấy userId từ session
        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println("Attempting to get user details for userId: " + userId);

        if (userId != null) {
            User user = userService.getUserById(userId);
            if (user != null) {
                System.out.println("Found user: " + user.toString());
                request.setAttribute("user", user);
            } else {
                System.out.println("No user found for ID: " + userId);
            }
        } else {
            System.out.println("UserId is null in session");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher("user/user-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
