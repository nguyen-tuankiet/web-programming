package com.example.backend.controller.Products;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "filterProduct", value = "/product/filter")

public class FilterProductsController extends HttpServlet {

    ProductService productService = new ProductService(DBConnection.getJdbi());

    private static final Logger log = LoggerFactory.getLogger(FilterProductsController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String , Object> jsonMap = mapper.readValue(request.getReader(), Map.class);

            int categoryId = Integer.parseInt(jsonMap.get("categoryId").toString());
            List<Integer> options = (List<Integer>) jsonMap.get("optionsId");
            Integer maxPrice = (Integer) jsonMap.get("maxPrice");
            Integer minPrice = (Integer) jsonMap.get("minPrice");

            log.info("categoryId: " + categoryId);
            log.info("options: " + options);
            log.info("maxPrice: " + maxPrice);
            log.info("minPrice: " + minPrice);


            List<Product> products = productService.filterProducts(categoryId, options,  minPrice, maxPrice);


            log.info("Products List: " + products);


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(products));





        }catch (Exception e) {
            e.printStackTrace();
        }



    }
}
