package com.example.backend.controller;

import com.example.backend.model.DAO.ImageDao;
import com.example.backend.model.DAO.ProductDAO;
import com.example.backend.service.ProductService;
import com.example.backend.model.Product;
import com.example.backend.util.ResponseWrapper;
import org.jdbi.v3.core.Jdbi;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "ProductController", urlPatterns = {"/products"})
public class ProductController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        // Lấy thông tin kết nối từ application.properties
        String dbUrl = System.getenv("DB_URL");  // Hoặc lấy từ file cấu hình
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUrl == null || dbUsername == null || dbPassword == null) {
            throw new ServletException("Database connection parameters are missing");
        }

        try {
            // Khởi tạo Jdbi với thông tin kết nối
            Jdbi jdbi = Jdbi.create(dbUrl, dbUsername, dbPassword);

            // Khởi tạo ProductService và các DAOs
            ProductDAO productDAO = jdbi.onDemand(ProductDAO.class);
            ImageDao imageDao = jdbi.onDemand(ImageDao.class);
            productService = new ProductService(jdbi, productDAO, imageDao);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize database connection", e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Lấy các tham số từ form
            String name = request.getParameter("name");
            String sku = request.getParameter("sku");
            String description = request.getParameter("description");
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Integer brandId = Integer.parseInt(request.getParameter("brandId"));
            Integer price = Integer.parseInt(request.getParameter("price"));

            // Tạo đối tượng Product từ dữ liệu
            Product product = new Product(null, name, sku, description, true, categoryId, brandId, 0, 0, null, price);

            // Lấy ảnh từ yêu cầu
            List<String> imageUrls = new ArrayList<>();
            for (Part part : request.getParts()) {
                if (part.getName().equals("images") && part.getSize() > 0) {
                    String imageUrl = uploadImage(part.getInputStream()); // Giả lập upload
                    imageUrls.add(imageUrl);
                }
            }

            if (imageUrls.isEmpty()) {
                throw new IllegalArgumentException("Phải có ít nhất một ảnh.");
            }

            // Thêm sản phẩm và ảnh
            Product addedProduct = productService.addProduct(product, imageUrls);

            // Trả về phản hồi thành công với dữ liệu sản phẩm đã thêm
            ResponseWrapper<Product> responseWrapper = new ResponseWrapper<>(200, "success", "Sản phẩm đã được thêm thành công", addedProduct);
            String jsonResponse = convertToJson(responseWrapper);
            response.getWriter().write(jsonResponse);

        } catch (Exception e) {
            // Xử lý lỗi
            ResponseWrapper<String> errorWrapper = new ResponseWrapper<>(500, "error", "Có lỗi xảy ra: " + e.getMessage(), null);
            response.getWriter().write(convertToJson(errorWrapper));
        }
    }

    // Phương thức upload ảnh (giả lập)
    private String uploadImage(InputStream inputStream) {
        // Giả lập logic upload ảnh
        return "http://example.com/image.jpg"; // Mock URL
    }

    // Phương thức convert đối tượng thành JSON (có thể sử dụng thư viện Jackson hoặc Gson)
    private String convertToJson(Object object) {
        // Giả lập sử dụng thư viện Jackson hoặc Gson
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            return "{\"status\": \"error\", \"message\": \"Không thể chuyển đổi đối tượng thành JSON\"}";
        }
    }
}
