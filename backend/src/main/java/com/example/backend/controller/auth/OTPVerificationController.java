package com.example.backend.controller.auth;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/verify-otp")
public class OTPVerificationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredOtp = request.getParameter("otp");
        String sessionOtp = (String) request.getSession().getAttribute("otp");

        if (enteredOtp != null && enteredOtp.equals(sessionOtp)) {
            response.sendRedirect("/forgotpassword.jsp");
        } else {
            request.setAttribute("errorMessage", "Mã OTP không chính xác");
            request.getRequestDispatcher("/forgotpassword.jsp").forward(request, response);
        }
    }
}