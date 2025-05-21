package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.AuthService;
import com.example.backend.service.EmailService;
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
public class  RegisterController extends HttpServlet {

    private final AuthService authService = new AuthService(DBConnection.getJdbi());
    private final EmailService emailService = new EmailService();  // Khởi tạo EmailService

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

            String fullName = jsonData.get("fullName");
            String displayName = jsonData.get("displayName");
            String email = jsonData.get("email");
            String inputPassword = jsonData.get("password");
            String confirmPassword = jsonData.get("confirmPassword");

            // Kiểm tra mật khẩu
            if (inputPassword == null || inputPassword.isEmpty()) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(400, "error", "Password cannot be null or empty", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
                return;
            }

            if (!inputPassword.equals(confirmPassword)) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(400, "error", "Passwords do not match", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
                return;
            }

            // Đăng ký người dùng
            if (authService.register(fullName, displayName, email, inputPassword)) {
                // Tạo sessionId cho người dùng mới đăng ký
                String sessionId = request.getSession().getId();

                // Lưu thông tin session vào cơ sở dữ liệu nếu cần
                authService.saveSessionId(request, email, sessionId);

                // Gửi email xác nhận
//                emailService.sendConfirmationEmail(email, sessionId);

                // Chuẩn bị thông tin người dùng để trả về
                Map<String, String> userData = Map.of(
                        "fullName", fullName,
                        "displayName", displayName,
                        "email", email
                );

                ResponseWrapper<Map<String, String>> responseWrapper = new ResponseWrapper<>(201, "success", "Registration successful. Please check your email to confirm your account.", userData);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
            } else {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(409, "error", "Email already exists", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
            }

        } catch (Exception e) {
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(500, "error", "An error occurred: " + e.getMessage(), null);
            response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
        }
    }

}
