package com.example.backend.controller.search;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchResults", urlPatterns = {"/search-results"})
public class Search_Results extends HttpServlet {
    private final ProductService productService = new ProductService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int page = 1;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // Nếu không truyền page, mặc định là 1
        }

        int limit = 9;
        int offset = (page - 1) * limit;

        List<Product> products = productService.searchProducts(name, limit, offset);

        // Debug: In số lượng sản phẩm tìm thấy
//        System.out.println("Search keyword: " + name);
//        System.out.println("Số lượng sản phẩm tìm thấy: " + products.size());

        request.setAttribute("searchKeyword", name);
        request.setAttribute("products", products);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("/search/search-results.jsp").forward(request, response);
    }
}
