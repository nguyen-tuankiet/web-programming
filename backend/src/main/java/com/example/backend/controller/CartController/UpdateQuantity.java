package com.example.backend.controller.CartController;
import com.example.backend.model.DAO.cart.Cart;
import com.example.backend.model.DAO.cart.ProductCart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "IncreaseQuantity", value = "/cart/update-quantity")
public class UpdateQuantity extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart != null) {
                Integer productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                if (cart.getData().containsKey(productId)) {
                    ProductCart productCart = cart.getData().get(productId);
                    productCart.setQuantity(quantity);
                    cart.getData().put(productId, productCart);
                }
            }


        }

    }
}
