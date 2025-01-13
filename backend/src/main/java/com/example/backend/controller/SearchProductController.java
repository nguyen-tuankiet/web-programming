package com.example.backend.controller;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import com.example.backend.util.ResponseWrapper;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home/products/search")
public class SearchProductController extends HttpServlet {

    private final ProductService productService = new ProductService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new ResponseWrapper<>(400, "error", "Keyword must not be empty", null).getStatusCode());
            return;
        }

        List<Product> products = productService.searchProducts(name);

        if (products.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");
            response.getWriter().write(new ResponseWrapper<>(404, "error", "No products found", null).toJson());
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write(new ResponseWrapper<>(200, "success", "Products found", products).toJson());
        }

    }
}
