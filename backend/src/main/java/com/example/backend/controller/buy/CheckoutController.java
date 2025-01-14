package com.example.backend.controller.buy;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.model.Card;
import com.example.backend.model.DAO.cart.Cart;
import com.example.backend.model.DAO.cart.ProductCart;
import com.example.backend.model.Order;
import com.example.backend.model.OrderDetail;
import com.example.backend.service.AddressSevice;
import com.example.backend.service.CardService;
import com.example.backend.service.OrderDetailService;
import com.example.backend.service.OrderSerivce;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Checkout", value = "/checkout")
public class CheckoutController extends HttpServlet {
    OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());
    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
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


        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        Boolean flag = false;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);

        }

        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        String address = jsonObject.getString("address_id");
        String card = jsonObject.getString("card");
        JSONArray products = jsonObject.getJSONArray("products");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        //TODO: get user from section

        Order order = new Order();
        order.setCreateAt(LocalDate.now());
        order.setPaymentStatus("PAID");
        order.setOrderStatus("DELIVERY");
        order.setUserId(userId);
        try {
            order.setAddressId(Integer.parseInt(address));
            if (card.equals("COD")) {
                order.setIsCOD(true);
            }
            else{
                order.setCardId(Integer.parseInt(card));
                order.setIsCOD(false);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        Integer orderid = orderSerivce.addOrder(order);

        if (orderid != null) {
            Integer oderid  = orderid;
            for (int i = 0; i < products.length(); i++) {
                JSONObject product = products.getJSONObject(i);

                int productId = product.getInt("id");
                int quantity = product.getInt("quantity");
                int total = product.getInt("total");
                int optionId = product.getInt("optionId");

                OrderDetail od= new OrderDetail();
                od.setOrderId(oderid);
                od.setProductId(productId);
                od.setQuantity(quantity);
                od.setTotal(total);
                od.setOptionId(optionId);

                flag= orderDetailService.addOrderDetail(od);


            }
        }

       if (flag) {
           JSONObject jsonResponse = new JSONObject();
           jsonResponse.put("success", true);
           response.getWriter().write(jsonResponse.toString());
       }else {
  

           JSONObject jsonResponse = new JSONObject();
           jsonResponse.put("success", false);

           response.getWriter().write(jsonResponse.toString());
       }


    }
}
