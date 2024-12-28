package com.example.backend.controller.CartController;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.cart.Cart;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddToCart", value = "/add-cart")
public class AddToCart extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer productId=  Integer.parseInt(request.getParameter("productId"));
        Integer optionId =Integer.parseInt(request.getParameter("optionId"));

        Product product = productService.getProductByIdAndOptionId(productId,optionId);

        HttpSession session = request.getSession();
        Cart cart= (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.addProduct(product);

    }
}
