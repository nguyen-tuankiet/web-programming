package com.example.backend.controller.admin.product;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.ProductDTO;
import com.example.backend.service.ImageService;
import com.example.backend.service.OptionService;
import com.example.backend.service.ProductService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "EditProductController", value = "/admin/editProduct")
public class EditProductController extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());
    ImageService imageService = new ImageService(DBConnection.getJdbi());
    OptionService optionService = new OptionService(DBConnection.getJdbi());
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            ProductDTO productDTO = productService.editProductById(productId);

            if (productDTO == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"message\": \"Product not found\"}");
                return;
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new ResponseWrapper<>(200, "Success", "Success", productDTO).toJson());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"Internal Server Error\", \"details\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Đọc dữ liệu JSON từ request body
            String requestBody = request.getReader().lines().collect(Collectors.joining());
            Map<String, Object> productData = objectMapper.readValue(requestBody, Map.class);

            // Lấy ID sản phẩm từ request
            Integer productId = Integer.parseInt(productData.get("id").toString());

            // Cập nhật thông tin sản phẩm cơ bản
            boolean updateSuccess = productService.updateProduct(
                    productId,
                    (String) productData.get("name"),
                    (String) productData.get("description"),
                    (String) productData.get("sku"),
                    Integer.parseInt(productData.get("categoryId").toString()),
                    Integer.parseInt(productData.get("brandId").toString()),
                    productData.get("primaryImage") != null ? Integer.parseInt(productData.get("primaryImage").toString()) : null,
                    Integer.parseInt(productData.get("height").toString()),
                    Integer.parseInt(productData.get("length").toString()),
                    Integer.parseInt(productData.get("width").toString()),
                    Integer.parseInt(productData.get("weight").toString())
            );

            // Cập nhật options nếu có
            if (productData.containsKey("options")) {
                List<Map<String, Object>> options = (List<Map<String, Object>>) productData.get("options");
                for (Map<String, Object> option : options) {
                    optionService.updateOption(
                            Integer.parseInt(option.get("id").toString()),
                            Integer.parseInt(option.get("price").toString()),
                            Integer.parseInt(option.get("stock").toString())
                    );
                }
            }

            if (updateSuccess) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new ResponseWrapper<>(200, "Success", "Product updated successfully", null).toJson());
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new ResponseWrapper<>(400, "Error", "Failed to update product", null).toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new ResponseWrapper<>(500, "Error", e.getMessage(), null).toJson());
        }
    }
}
