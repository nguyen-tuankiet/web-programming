package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.CategoryWithStock;
import com.example.backend.service.CategoryCustomService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListCategoryController", value = "/admin/category")
public class ListCategoryController extends HttpServlet {
    CategoryCustomService categoryCustomService =  new CategoryCustomService(DBConnection.getJdbi()); ;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<CategoryWithStock> categoriesWithStock = categoryCustomService.getCustomCategoriesWithTotalStock();
            request.setAttribute("categoriesWithStock", categoriesWithStock);
            request.getRequestDispatcher("categories.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải danh mục.");
        }
    }
}
