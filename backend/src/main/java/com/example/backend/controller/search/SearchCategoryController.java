package com.example.backend.controller.search;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.model.Variant;
import com.example.backend.model.VariantValue;
import com.example.backend.service.ProductService;
import com.example.backend.service.VariantService;
import com.example.backend.service.VariantValueService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchCategory", value = "/search-category")
public class SearchCategoryController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(SearchCategoryController.class);

    ProductService productService = new ProductService(DBConnection.getJdbi());
    VariantService variantService = new VariantService(DBConnection.getJdbi());
    VariantValueService variantValueService = new VariantValueService(DBConnection.getJdbi());
    ;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String param =  request.getParameter("categoryId");
        if (param == null) {
            throw new ServletException("Category ID is required");
        }
        int categoryId = Integer.parseInt(param);


        List<Product> products = productService.getProductsByCategory(categoryId);
        List<Product> topProducts = productService.getTopProductsByCategory(categoryId,3);
        List<Variant> variants = variantService.getVariantsByCategory(categoryId);

        for (Variant v : variants) {
            List<VariantValue> variantValues = variantValueService.getVariantValuesByVariantId(v.getId());

            v.setVariantValues(variantValues);

        }

        request.setAttribute("variants", variants);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("products", products);
        request.setAttribute("topProducts", topProducts);


        request.getRequestDispatcher("search/search-category.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
