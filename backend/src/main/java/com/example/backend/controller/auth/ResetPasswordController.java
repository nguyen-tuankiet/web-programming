package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.User;
import com.example.backend.service.AuthService;
import com.example.backend.util.HashUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/reset-password")
public class ResetPasswordController extends HttpServlet {
    private final AuthService authService = new AuthService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = (String) request.getSession().getAttribute("userEmail");

        if (newPassword.equals(confirmPassword)) {
            User user = authService.getUserByEmail(email);
            if (user != null) {
                try {
                    authService.changePassword(user.getId(),null, newPassword, false);

                    response.sendRedirect("/backend_war/login");
                } catch (IllegalArgumentException e) {
                    request.setAttribute("errorMessage", e.getMessage());
                    request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("errorMessage", "Mật khẩu không khớp");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
    }

}
