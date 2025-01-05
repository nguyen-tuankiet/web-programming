package com.example.backend.controller.CartController;

import com.example.backend.model.DAO.cart.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Remove", value = "/cart/remove")
public class Remove extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if( cart !=null){
            Integer productId = Integer.parseInt(request.getParameter("productId"));
            if (cart.getData().containsKey(productId)) {
                cart.getData().remove(productId);
            }
        }
    }
}
