package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.DeleteProductService;
import com.example.backend.service.ProductService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/admin/delete-product")
public class DeleteProductController extends HttpServlet {

    private DeleteProductService productService;

    @Override
    public void init() throws ServletException {
        this.productService = new DeleteProductService(DBConnection.getJdbi());
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc dữ liệu từ body của request
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }

// Phân tích JSON (lấy productId)
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(sb.toString(), Map.class);
        String productIdString = (String) jsonMap.get("productId");  // lấy productId là String

// Kiểm tra nếu productId là hợp lệ, chuyển nó sang Integer
        Integer productId = null;
        if (productIdString != null) {
            try {
                productId = Integer.valueOf(productIdString);  // Chuyển String sang Integer
            } catch (NumberFormatException e) {
                // Trường hợp khi không thể chuyển đổi giá trị thành Integer
                ResponseWrapper<String> responseWrapper = new ResponseWrapper<>(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "error",
                        "productId không hợp lệ",
                        null
                );
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write(responseWrapper.toJson());
                return;
            }
        }

// Kiểm tra nếu productId là null hoặc không hợp lệ
        if (productId == null) {
            ResponseWrapper<String> responseWrapper = new ResponseWrapper<>(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "error",
                    "productId không hợp lệ",
                    null
            );
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write(responseWrapper.toJson());
            return;
        }


        boolean isDeleted = productService.deactivateProduct(productId);
        try {

        if (isDeleted) {
                // Trả về phản hồi thành công nếu xóa thành công
                ResponseWrapper<String> responseWrapper = new ResponseWrapper<>(
                        HttpServletResponse.SC_OK,
                        "success",
                        "Sản phẩm đã được xóa",
                        null
                );
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(responseWrapper.toJson());
            } else {
                // Trả về lỗi nếu không xóa được sản phẩm
                ResponseWrapper<String> responseWrapper = new ResponseWrapper<>(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "error",
                        "Không thể xóa sản phẩm",
                        null
                );
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                response.getWriter().write(responseWrapper.toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Trả về lỗi server nếu có ngoại lệ xảy ra
            ResponseWrapper<String> responseWrapper = new ResponseWrapper<>(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "error",
                    "Đã xảy ra lỗi khi xử lý yêu cầu",
                    null
            );
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.getWriter().write(responseWrapper.toJson());
        }
    }
}
