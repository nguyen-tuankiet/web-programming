package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.OptionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet(name = "OptionController", urlPatterns = {"/admin/api/options/create"})
public class OptionController extends HttpServlet {

    private final OptionService optionService = new OptionService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String productIdStr = request.getParameter("productId");
            String priceStr = request.getParameter("price");
            String stockStr = request.getParameter("stock");

            Integer productId = (productIdStr != null && !productIdStr.isEmpty()) ? Integer.parseInt(productIdStr) : null;
            Integer price = (priceStr != null && !priceStr.isEmpty()) ? Integer.parseInt(priceStr) : null;
            Integer stock = (stockStr != null && !stockStr.isEmpty()) ? Integer.parseInt(stockStr) : null;

            int optionId = optionService.createOptions(productId, price, stock);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("{\"message\": \"Option created successfully\", \"optionId\": " + optionId + "}");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid number format for one or more parameters\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
