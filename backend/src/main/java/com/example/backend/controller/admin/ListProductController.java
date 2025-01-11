package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "listProduct", value = "/admin/list-product")
public class ListProductController extends HttpServlet {

     ProductService productService = new ProductService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy danh sách sản phẩm
            List<Product> products = productService.getAllProducts();

            // Đưa danh sách sản phẩm vào request
            request.setAttribute("products", products);

            // Forward sang JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("listProduct.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải danh sách sản phẩm");
        }
    }
}
