package com.example.backend.controller.ProductDetail;

import com.example.backend.service.ImageDetailService;
import com.example.backend.model.ImageDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/product-carousel")
public class ProductCarouselController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            ImageDetailService imageService = new ImageDetailService(connection);

            List<ImageDetail> images = imageService.getAllImageDetails();

            req.setAttribute("images", images);

            req.getRequestDispatcher("/Product-detail/Product-detail-item.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving images", e);
        }
    }
}
