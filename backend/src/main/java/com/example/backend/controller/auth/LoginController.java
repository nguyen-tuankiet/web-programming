package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.AuthService;
import com.example.backend.model.User;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {

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
            Map<String, String> jsonData = objectMapper.readValue(jsonString, Map.class);
            String email = jsonData.get("email");
            String password = jsonData.get("password");

            // Kiểm tra thông tin đầu vào
            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        400, "error", "Email và mật khẩu không được để trống", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
                return;
            }

            // Xử lý đăng nhập
            User user = authService.login(email, password);
            if (user != null) {
                // Lưu thông tin người dùng vào session
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("role", user.getRole()); // Lưu thông tin role (nếu có)

                // Trả về thông tin người dùng
                Map<String, String> userData = Map.of(
                        "id", user.getId(),
                        "fullName", user.getFullName(),
                        "displayName", user.getDisplayName(),
                        "email", user.getEmail(),
                        "role", user.getRole(),
                        "sessionId", session.getId()
                );


                ResponseWrapper<Map<String, String>> responseWrapper = new ResponseWrapper<>(
                        200, "success", "Đăng nhập thành công", userData);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
            } else {
                // Trả về lỗi nếu thông tin đăng nhập không hợp lệ
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        401, "error", "Email hoặc mật khẩu không chính xác", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
            }
        } catch (Exception e) {
            // Xử lý lỗi hệ thống
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    500, "error", "Đã xảy ra lỗi: " + e.getMessage(), null);
            response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/auth/auth.jsp").forward(request, response);
    }

}
