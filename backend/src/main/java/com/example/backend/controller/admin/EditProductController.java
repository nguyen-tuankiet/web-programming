package com.example.backend.controller.admin;


import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Options;
import com.example.backend.model.Product;
import com.example.backend.model.ProductDTO;
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
import java.util.Map;

@WebServlet(name = "EditProductController", value = "/admin/editProduct")
public class EditProductController extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());
    ImageService imageService = new ImageService(DBConnection.getJdbi());
    OptionService optionService = new OptionService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            ProductDTO productDTO = productService.editProductById(productId);

            if (productDTO == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"message\": \"Product not found\"}");
                return;
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new ResponseWrapper<>(200, "Success", "Success", productDTO).toJson());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"Internal Server Error\", \"details\": \"" + e.getMessage() + "\"}");
        }

    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.getWriter().write("{\"message\": \"POST method is not allowed\"}");
    }
}
