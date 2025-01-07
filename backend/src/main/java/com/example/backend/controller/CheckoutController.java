package com.example.backend.controller;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.model.Card;
import com.example.backend.model.DAO.cart.Cart;
import com.example.backend.model.DAO.cart.ProductCart;
import com.example.backend.service.AddressSevice;
import com.example.backend.service.CardService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Checkout", value = "/checkout")
public class CheckoutController extends HttpServlet {

    CardService cardService = new CardService(DBConnection.getJdbi());
    AddressSevice addressService = new AddressSevice(DBConnection.getJdbi());



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session = request.getSession();
        List<ProductCart > productList = new ArrayList<>();

        List<Address> addressList = new ArrayList<>();
        List<Card> cardList = new ArrayList<>();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }


        String productParam = request.getParameter("productIds");

        if ( productParam != null) {

            String[] arrProducts = productParam.split(",");
            for (String product : arrProducts) {
                if (cart.getData().containsKey(Integer.parseInt(product))) {
                    productList.add(cart.getData().get(Integer.parseInt(product)));
                }
            }

        }

        addressList = addressService.findByUserId(1);
        cardList = cardService.getCartByUserId(1);





        request.setAttribute("productList", productList);
        request.setAttribute("addressList", addressList);
        request.setAttribute("cardList", cardList);



        request.getRequestDispatcher("Checkout/Checkout.jsp").forward(request, response);




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
