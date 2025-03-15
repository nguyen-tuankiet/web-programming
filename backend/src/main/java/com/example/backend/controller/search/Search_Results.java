package com.example.backend.controller.search;
import com.example.backend.Connection.DBConnection;
import com.example.backend.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException; 
@WebServlet(name = "Search_Results", value = "/search-results")
public class Search_Results extends HttpServlet {
@Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("search/search-results.jsp").forward(request, response);
}
@Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { } 
}