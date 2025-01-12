package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.OptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "OptionController", urlPatterns = {"/admin/options/create"})
public class OptionController extends HttpServlet {

    private final OptionService optionService = new OptionService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Đọc payload JSON từ request
            StringBuilder payload = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    payload.append(line);
                }
            }

            // Chuyển đổi JSON thành đối tượng Java
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> requestData = objectMapper.readValue(payload.toString(), Map.class);

            // Lấy giá trị từ JSON
            Integer productId = (Integer) requestData.get("productId");
            Integer price = (Integer) requestData.get("price");
            Integer stock = (Integer) requestData.get("stock");

            // Gọi service để tạo option
            int optionId = optionService.createOptions(productId, price, stock);

            // Trả về kết quả
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("{\"message\": \"Option created successfully\", \"optionId\": " + optionId + "}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

}
