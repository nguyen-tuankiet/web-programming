package com.example.backend.controller.ProductDetail;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.service.ImageService;
import com.example.backend.service.OptionService;
import com.example.backend.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductDetailController", value = "/product-detail")
public class ProductDetailController extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());
    ImageService imageService = new ImageService(DBConnection.getJdbi());
    OptionService optionService = new OptionService(DBConnection.getJdbi());




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = productService.getProductById(Integer.parseInt(request.getParameter("id")));
        List<String> images = imageService.getAllImagesByProductId(product.getId());
        List<String> descriptions = List.of(product.getDescription().split("\\n"));
        request.setAttribute("images", images);
        request.setAttribute("product", product);
        request.setAttribute("descriptions", descriptions);
        request.getRequestDispatcher("product_detail/ProductDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}