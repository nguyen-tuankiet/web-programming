package com.example.backend.controller.image;


import com.example.backend.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "ProductImageController", urlPatterns = {"/api/products/*/images"})
public class ProductImageController {
    private static final long serialVersionUID = 1L;

    private ImageService imageService = new ImageService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId = Integer.valueOf(request.getPathInfo().substring(1));
        Integer imageId = Integer.valueOf(request.getParameter("imageId"));

        imageService.addImageToProduct(productId, imageId);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
