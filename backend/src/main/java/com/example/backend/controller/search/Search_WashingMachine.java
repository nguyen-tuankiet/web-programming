package com.example.backend.controller.search;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Search_WashingMachine", value = "/search-washing-machine")
public class Search_WashingMachine extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productService.getProductsByCategory(2);
        List<Product> topProducts = productService.getTopProductsByCategory(2,3);

        request.setAttribute("products", products);
        request.setAttribute("topProducts", topProducts);

        request.getRequestDispatcher("search/search-washing-machine.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}



