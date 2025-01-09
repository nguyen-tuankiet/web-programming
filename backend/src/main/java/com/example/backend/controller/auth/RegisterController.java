package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.AuthService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

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
            String fullName = jsonData.get("fullName");
            String displayName = jsonData.get("displayName");
            String email = jsonData.get("email");
            String inputPassword = jsonData.get("password");
            String confirmPassword = jsonData.get("confirmPassword");

            // Kiểm tra các trường dữ liệu
            if (inputPassword == null || inputPassword.isEmpty()) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        400, "error", "Password cannot be null or empty", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
                return;
            }

            // Kiểm tra mật khẩu có trùng khớp với mật khẩu xác nhận không
            if (!inputPassword.equals(confirmPassword)) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        400, "error", "Passwords do not match", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
                return;
            }

            // Đăng ký người dùng
            if (authService.register(fullName, displayName, email, inputPassword)) {
                // Chuẩn bị thông tin người dùng để trả về
                Map<String, String> userData = Map.of(
                        "fullName", fullName,
                        "displayName", displayName,
                        "email", email
                );

                ResponseWrapper<Map<String, String>> responseWrapper = new ResponseWrapper<>(
                        201, "success", "Registration successful", userData);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
            } else {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        409, "error", "Email already exists", null);
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
