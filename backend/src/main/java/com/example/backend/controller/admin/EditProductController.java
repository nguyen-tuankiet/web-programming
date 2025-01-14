package com.example.backend.controller.admin;


import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Options;
import com.example.backend.model.Product;
import com.example.backend.service.ImageService;
import com.example.backend.service.OptionService;
import com.example.backend.service.ProductService;
import com.example.backend.util.ResponseWrapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditProductController", value = "/admin/editProduct")
public class EditProductController extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());
    ImageService imageService = new ImageService(DBConnection.getJdbi());
    OptionService optionService = new OptionService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy `id` từ request
            int productId = Integer.parseInt(request.getParameter("id"));

            // Lấy thông tin sản phẩm
            Product product = productService.getProductById(productId);
            if (product == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(new ResponseWrapper<>(404, "Not Found", "Product not found", null).toJson());
                return;
            }

            // Lấy thêm thông tin liên quan
            Integer productPrice = productService.getMinimumPriceForProduct(productId);
            if (product.getOptionId() != null) {
                productPrice = productService.getPriceForOption(product.getOptionId());
            }

            List<String> images = imageService.getAllImagesByProductId(product.getId());
            String primaryImageUrl = imageService.getImageUrlById(product.getPrimaryImage());
            List<String> descriptions = List.of(product.getDescription().split("\\n"));
            List<Options> options = optionService.getOptionsByProductId(product.getId());

            // Gắn thêm thông tin vào đối tượng `Product`
            product.setPrice(productPrice); // Cập nhật giá
            product.setImageUrl(primaryImageUrl); // Cập nhật hình ảnh chính
            product.setDescription(String.join("\n", descriptions)); // Cập nhật mô tả

            // Trả về JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ResponseWrapper<Product> responseWrapper = new ResponseWrapper<>(
                    200,
                    "Success",
                    "Product details fetched successfully",
                    product
            );
            response.getWriter().write(responseWrapper.toJson());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new ResponseWrapper<>(500, "Error", "Internal Server Error", null).toJson());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.getWriter().write("{\"message\": \"POST method is not allowed\"}");
    }

}
