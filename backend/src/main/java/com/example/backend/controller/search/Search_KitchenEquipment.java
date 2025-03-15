package com.example.backend.controller.search;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.model.Variant;
import com.example.backend.model.VariantValue;
import com.example.backend.service.ProductService;
import com.example.backend.service.VariantService;
import com.example.backend.service.VariantValueService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Search_KitchenEquipment", value = "/search-kitchen-equipment")
public class Search_KitchenEquipment extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());
    VariantService variantService = new VariantService(DBConnection.getJdbi());
    VariantValueService variantValueService = new VariantValueService(DBConnection.getJdbi());
    int categoryId = 6;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productService.getProductsByCategory(categoryId);
        List<Product> topProducts = productService.getTopProductsByCategory(categoryId,3);
        List<Variant> variants = variantService.getVariantsByCategory(categoryId);

        for (Variant v : variants) {
            List<VariantValue> variantValues = variantValueService.getVariantValuesByVariantId(v.getId());

            v.setVariantValues(variantValues);

        }

        request.setAttribute("variants", variants);
        request.setAttribute("products", products);
        request.setAttribute("topProducts", topProducts);

        request.getRequestDispatcher("search/search-kitchen-equipment.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}