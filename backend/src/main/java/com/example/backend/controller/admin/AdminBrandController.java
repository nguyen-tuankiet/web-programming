package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Brand;
import com.example.backend.service.BrandService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminBrandController", value = "/admin/brand")
public class AdminBrandController extends HttpServlet {
    BrandService brandService = new BrandService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Brand> brands = brandService.getAllBrands();
        request.setAttribute("brands", brands);

        request.getRequestDispatcher("brand.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
