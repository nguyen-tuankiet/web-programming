package com.example.backend.controller.ProductDetail;

import com.example.backend.service.ProductImageService;
import com.example.backend.model.ProductImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/product-images")
public class ProductImagesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        try (Connection connection = DatabaseConnection.getConnection()) {
            ProductImageService productImageService = new ProductImageService(connection);

            List<ProductImage> productImages = productImageService.getImagesByProductId(productId);

            req.setAttribute("productImages", productImages);

            req.getRequestDispatcher("/Product-detail/Product-detail-item.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving product images", e);
        }
    }
}
