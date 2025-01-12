package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.AuthService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {
    private final AuthService authService = new AuthService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Đọc nội dung JSON từ body request
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }
            }
            String jsonString = jsonBuilder.toString();

            // Parse JSON để lấy dữ liệu
            Map<String, String> jsonData = objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {});

            // Lấy các tham số từ JSON
            String currentPassword = jsonData.get("currentPassword");
            String newPassword = jsonData.get("newPassword");
            String confirmPassword = jsonData.get("confirmPassword");


            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");


            // Kiểm tra mật khẩu mới và xác nhận mật khẩu có khớp không
            if (newPassword == null || newPassword.isEmpty()) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        400, "error", "New password cannot be null or empty", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        400, "error", "New password and confirmation do not match", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
                return;
            }

            // Gọi UserService để thay đổi mật khẩu
            boolean isPasswordChanged = authService.changePassword(userId, currentPassword, newPassword);
            if (isPasswordChanged) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        200, "success", "Password changed successfully", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
            } else {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        400, "error", "Current password is incorrect", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
            }

        } catch (Exception e) {
            // Xử lý lỗi và trả về phản hồi phù hợp
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    500, "error", "An error occurred: " + e.getMessage(), null);
            response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
        }
    }
}
