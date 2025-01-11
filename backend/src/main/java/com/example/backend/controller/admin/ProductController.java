package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;

import com.example.backend.service.ProductService;
import com.example.backend.model.Product;

import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet(name = "ProductController", value = {"/admin/products"})
public class ProductController extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {  // Đọc dữ liệu JSON từ request body
        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // Chuyển đổi JSON thành đối tượng Product (bạn có thể sử dụng thư viện như Jackson hoặc Gson)
        Product product = new ObjectMapper().readValue(json.toString(), Product.class);

        // Gọi service để thêm sản phẩm
        Product newProduct = productService.addProduct(product);

        response.setStatus(HttpServletResponse.SC_OK);
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                201, "success", "Product created successfully", newProduct);
        writeResponse(response, responseWrapper);
    } catch (IllegalArgumentException e) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                400, "error", e.getMessage(), null);
        writeResponse(response, responseWrapper);
    } catch (Exception e) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                500, "error", "Internal server error: " + e.getMessage(), null);
            writeResponse(response, responseWrapper);
    }


}
    private void writeResponse(HttpServletResponse response, ResponseWrapper<?> responseWrapper) throws IOException {
        response.setContentType("application/json");
        response.setStatus(responseWrapper.getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseWrapper));
    }
}