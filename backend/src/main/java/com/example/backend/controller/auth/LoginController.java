package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.AuthService;
import com.example.backend.model.User;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jdbi.v3.core.Jdbi;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private final AuthService authService = new AuthService(DBConnection.getJdbi());
    private static final Dotenv dotenv = Dotenv.configure()
            .load();

    private static final String SECRET_KEY = dotenv.get("RECAPTCHA_SECRET_KEY");

    private boolean verifyRecaptcha(String token) {
        try {
            URL url = new URL("https://www.google.com/recaptcha/api/siteverify");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String postData = "secret=" + SECRET_KEY + "&response=" + token;
            conn.getOutputStream().write(postData.getBytes(StandardCharsets.UTF_8));

            try (InputStream input = conn.getInputStream();
                 InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                 JsonReader jsonReader = new JsonReader(reader)) {

                JsonObject jsonObject = com.google.gson.JsonParser.parseReader(jsonReader).getAsJsonObject();
                return jsonObject.get("success").getAsBoolean();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


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
            String recaptchaToken = jsonData.get("recaptcha");

            // Kiểm tra thông tin đầu vào
            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        400, "error", "Email và mật khẩu không được để trống", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
                return;
            }

            //  Kiểm tra reCAPTCHA
            if (recaptchaToken == null || !verifyRecaptcha(recaptchaToken)) {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        400, "error", "Xác thực reCAPTCHA không thành công.", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
                return;
            }

            // Xử lý đăng nhập
            User user = authService.login(email, password);

            if (user != null) {
                // Lưu thông tin người dùng vào session
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                // Lưu role name thay vì role object để tương thích với session
                session.setAttribute("role", user.getRole() != null ? user.getRole().getName() : "USER");

                // Trả về thông tin người dùng
                Map<String, String> userData = Map.of(
                        "id", String.valueOf(user.getId()),
                        "fullName", user.getFullName(),
                        "displayName", user.getDisplayName(),
                        "email", user.getEmail(),
                        "role", user.getRole() != null ? user.getRole().getName() : "USER", // Sử dụng role name
                        "status", user.getStatus(),
                        "sessionId", session.getId()
                );

                ResponseWrapper<Map<String, String>> responseWrapper = new ResponseWrapper<>(
                        200, "success", "Đăng nhập thành công", userData);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
            } else {
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        401, "error", "Có lỗi khi đăng nhập! Vui lòng kiểm tra lại.", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
            }
        } catch (RuntimeException e) {
            // Xử lý các lỗi liên quan đến trạng thái tài khoản
            ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                    401, "error", e.getMessage(), null);
            response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
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