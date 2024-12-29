package com.example.backend.controller.image;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.ImageService;
import com.fasterxml.jackson.databind.ObjectMapper; // Thêm thư viện JSON để xử lý JSON
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "ProductImageController", urlPatterns = {"/api/products/{productId}/images"})
public class ProductImageController extends HttpServlet {

    ImageService imageService = new ImageService(DBConnection.getJdbi());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy productId từ phần còn lại của URL (sau "/api/products/")
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.split("/").length > 1) {
            String productIdStr = pathInfo.split("/")[1];
            Integer productId = Integer.valueOf(productIdStr);

            // Đọc JSON body từ request
            ObjectMapper objectMapper = new ObjectMapper();
            ProductImageRequest productImageRequest = objectMapper.readValue(request.getInputStream(), ProductImageRequest.class);

            Integer imageId = productImageRequest.getImageId();

            // Thêm ảnh vào sản phẩm
            imageService.addImageToProduct(productId, imageId);

            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Product ID is missing in the URL");
        }
    }

    // DTO để chứa thông tin imageId từ request body
    public static class ProductImageRequest {
        private Integer imageId;

        public Integer getImageId() {
            return imageId;
        }

        public void setImageId(Integer imageId) {
            this.imageId = imageId;
        }
    }
}
