package com.example.backend.controller.search;


import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Search", urlPatterns = {"/suggest"})

public class SuggestController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(SuggestController.class);
    private final ProductService productService = new ProductService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.suggestProducts();
        log.info(products.toString());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = new com.google.gson.Gson().toJson(products);
        response.getWriter().write(json);
    }
}
