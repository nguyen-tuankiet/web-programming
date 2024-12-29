package com.example.backend.controller;

import com.example.backend.Connection.DBConnection;

import com.example.backend.service.ProductService;
import com.example.backend.model.Product;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet(name = "ProductController", urlPatterns = {"/products"})
public class ProductController extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc dữ liệu JSON từ request body
        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // Chuyển đổi JSON thành đối tượng Product (bạn có thể sử dụng thư viện như Jackson hoặc Gson)
        Product product = new ObjectMapper().readValue(json.toString(), Product.class);

        // Gọi service để thêm sản phẩm
        productService.addProduct(product);

        response.setStatus(HttpServletResponse.SC_OK);
    }


}
