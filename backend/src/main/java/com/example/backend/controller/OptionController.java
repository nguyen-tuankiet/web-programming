package com.example.backend.controller;


import com.example.backend.model.Options;
import com.example.backend.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OptionController {

    private static final long serialVersionUID = 1L;

    private ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId = Integer.valueOf(request.getPathInfo().substring(1));

        Integer price = Integer.valueOf(request.getParameter("price"));
        Integer stock = Integer.valueOf(request.getParameter("stock"));

        Options option = new Options();
        option.setProductId(productId);
        option.setPrice(price);
        option.setStock(stock);

        productService.addOptionToProduct(productId, price, stock);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
