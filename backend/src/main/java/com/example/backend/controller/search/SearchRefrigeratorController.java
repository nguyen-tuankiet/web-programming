package com.example.backend.controller.search;
import com.example.backend.Connection.DBConnection;
import com.example.backend.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import com.example.backend.model.Product;

@WebServlet(name = "SearchRefrigerator", value = "/search-refrigerator")
public class SearchRefrigeratorController extends HttpServlet {

    ProductService productService = new ProductService(DBConnection.getJdbi());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Product> products = productService.getProductsByCategory(1);

        request.setAttribute("products", products);

        request.getRequestDispatcher("search/search-refrigerator.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
