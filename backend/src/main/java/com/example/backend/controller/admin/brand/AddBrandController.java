package com.example.backend.controller.admin.brand;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.BrandService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.cloudinary.json.JSONObject;

import java.io.IOException;

@WebServlet(name = "AddBrandController", value = "/admin/add-brand")
public class AddBrandController extends HttpServlet {

    BrandService brandService = new BrandService(DBConnection.getJdbi());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            jsonString.append(line);
        }

        JSONObject jsonRequest = new JSONObject(jsonString.toString());

        String brandName = jsonRequest.getString("brand");
        boolean isActive = jsonRequest.getBoolean("isActive");

        brandService.createBrand(brandName, isActive);

        // Phản hồi cho client
        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"success\", \"message\": \"Brand added successfully\"}");
    }

}
