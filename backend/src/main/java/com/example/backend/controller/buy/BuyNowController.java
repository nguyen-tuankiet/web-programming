package com.example.backend.controller.buy;
import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.model.Card;
import com.example.backend.model.DAO.cart.ProductCart;
import com.example.backend.model.Product;
import com.example.backend.service.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BuyNowController", value = "/buy-now")
public class BuyNowController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(BuyNowController.class);
    ProductService productService = new ProductService(DBConnection.getJdbi());
    OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
    CardService cardService = new CardService(DBConnection.getJdbi());
    AddressService addressService = new AddressService(DBConnection.getJdbi());



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Product product = productService.getProductByIdAndOptionId(Integer.parseInt(request.getParameter("productId")),
                Integer.parseInt(request.getParameter("optionId")));


        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (product == null) {
            throw new ServletException("Product not found");
        }
        ProductCart productCart = new ProductCart(product);

         List<ProductCart> productList = new ArrayList<>();
            productList.add(productCart);

        List<Address> addressList = new ArrayList<>();
        List<Card> cardList = new ArrayList<>();


        addressList = addressService.findByUserId(userId);
        cardList = cardService.getCartByUserId(userId);





        request.setAttribute("productList", productList);
        request.setAttribute("addressList", addressList);
        request.setAttribute("cardList", cardList);



        request.getRequestDispatcher("Checkout/Checkout.jsp").forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String productId = request.getParameter("productId");
            String optionId = request.getParameter("optionId");

            if (productId == null || optionId == null) {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("success", false);
                errorResponse.put("message", "Missing required parameters");
                response.getWriter().write(errorResponse.toString());
                return;
            }

            Product product = productService.getProductByIdAndOptionId(
                Integer.parseInt(productId),
                Integer.parseInt(optionId)
            );

            if (product == null) {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("success", false);
                errorResponse.put("message", "Product not found");
                response.getWriter().write(errorResponse.toString());
                return;
            }

            JSONObject successResponse = new JSONObject();
            successResponse.put("success", true);
            successResponse.put("message", "Product found");
            response.getWriter().write(successResponse.toString());

        } catch (Exception e) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("success", false);
            errorResponse.put("message", "An error occurred: " + e.getMessage());
            response.getWriter().write(errorResponse.toString());
        }
    }
}
