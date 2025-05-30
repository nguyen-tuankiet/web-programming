package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.OtpService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/verify-otp")
public class OTPVerificationController extends HttpServlet {
    private final OtpService otpService = new OtpService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredOtp = request.getParameter("otpCode");
        String email = request.getParameter("email");
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        System.out.println("Received OTP verification request - Email: " + email + ", OTP: " + enteredOtp);

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email is empty");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Email không được để trống");
            return;
        }

        if (enteredOtp == null || enteredOtp.length() != 5) {
            System.out.println("Invalid OTP format: " + enteredOtp);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Mã OTP không hợp lệ");
            return;
        }

        try {
            boolean isValid = otpService.verifyOTP(email, enteredOtp);

            if (isValid) {
                System.out.println("OTP verification successful for email: " + email);
                // Lưu email vào session để dùng cho bước reset password
                request.getSession().setAttribute("resetEmail", email);
                
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Xác thực OTP thành công");
            } else {
                System.out.println("OTP verification failed for email: " + email);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Mã OTP không chính xác hoặc đã hết hạn");
            }
        } catch (Exception e) {
            System.err.println("Error during OTP verification: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Đã xảy ra lỗi khi xác thực OTP");
        }
    }
}