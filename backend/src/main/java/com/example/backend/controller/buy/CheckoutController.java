package com.example.backend.controller.buy;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.OrderStatus;
import com.example.backend.contant.PaymentStatus;
import com.example.backend.controller.GHNApiCaller;
import com.example.backend.model.*;
import com.example.backend.model.DAO.cart.Cart;
import com.example.backend.model.DAO.cart.ProductCart;
import com.example.backend.model.request.GHNCreateOrderRequest;
import com.example.backend.model.request.GHNItem;
import com.example.backend.model.response.APIResponse;
import com.example.backend.model.response.CreateOrderResponse;
import com.example.backend.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
    AddressService addressService = new AddressService(DBConnection.getJdbi());
    ProductService productService = new ProductService(DBConnection.getJdbi());
    UserService userService = new UserService(DBConnection.getJdbi());

    int codAmount;
    StringBuilder content = new StringBuilder();
    List<GHNItem>items = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
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




        addressList = addressService.findByUserId(userId);
        if (addressList == null || addressList.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/user-address?requireAddress=true");
            return;
        }
        cardList = cardService.getCartByUserId(userId);





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
        String addressId = jsonObject.getString("address_id");
        String card = jsonObject.getString("card");
        int shipping_fee = jsonObject.getInt("ship_fee");
        codAmount   =shipping_fee;
        JSONArray products = jsonObject.getJSONArray("products");

        // User
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        if (user == null) throw new RuntimeException("User not found");

        // Address
        Address address = addressService.findById(Integer.parseInt(addressId));
        if ((address == null) || !(address.getUserId().equals(userId)))
            throw new RuntimeException("Address not found");



        // Order
        Order order = new Order();
        order.setCreateAt(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setShippingFee(shipping_fee);
        order.setUserId(userId);
        order.setAddressId(Integer.parseInt(addressId));
        try {
            if (card.equals("COD")) {
                order.setIsCOD(true);
                order.setPaymentStatus(PaymentStatus.PENDING);
            }else{
                order.setCardId(Integer.parseInt(card));
                order.setIsCOD(false);
                order.setPaymentStatus(PaymentStatus.PAID);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        // Create order
        Integer orderId = orderSerivce.addOrder(order);

        if (orderId != null) {
            order.setId(orderId);
            for (int i = 0; i < products.length(); i++) {
                JSONObject product = products.getJSONObject(i);

                int productId = product.getInt("id");
                int optionId = product.getInt("optionId");
                Product p = productService.getProductByIdAndOptionId(productId, optionId);

                if (p == null) throw new RuntimeException("Product not found");
                if (p.getStock() <=0) throw new RuntimeException("Product sold out");

                int quantity = product.getInt("quantity");
                int total = product.getInt("total");


                // Prepare data
                codAmount+=total;
                content.append(p.getName()).append("\n");
                GHNItem item = new GHNItem(p, quantity);
                items.add(item);


                OrderDetail od= new OrderDetail();
                od.setOrderId(orderId);
                od.setProductId(productId);
                od.setQuantity(quantity);
                od.setTotal(total);
                od.setOptionId(optionId);

                flag= orderDetailService.addOrderDetail(od);
                if (flag){
                    productService.increaseNoOfSold(productId, quantity);
                }


            }
        }


        // Call GHN API
        if (codAmount > 50000000) throw new RuntimeException("Cod amount exceeds 50000000");
        GHNCreateOrderRequest GHNRequest= new GHNCreateOrderRequest(
                address, user, "Ten san pham", card.equals("COD") ? codAmount: 0, items);

        String GHNResponse = GHNCreateOrder(GHNRequest);
        ObjectMapper mapper = new ObjectMapper();
        APIResponse<CreateOrderResponse> apiResponse = mapper.readValue(GHNResponse,  new TypeReference<APIResponse<CreateOrderResponse>>() {});

        if (apiResponse.getCode() == 200) {
            orderSerivce.updateShippingId(order.getId(), apiResponse.getData().getOrder_code());
        }

        if (apiResponse.getCode() != 200) {
            orderSerivce.updateStatus(order.getId(), OrderStatus.ORDER_CREATE_ERROR);
        }




       if (flag){
           JSONObject jsonResponse = new JSONObject();
           jsonResponse.put("success", true);
           response.getWriter().write(jsonResponse.toString());
       }else {
           JSONObject jsonResponse = new JSONObject();
           jsonResponse.put("success", false);
           response.getWriter().write(jsonResponse.toString());
       }
    }

    private String GHNCreateOrder( GHNCreateOrderRequest GHNCreateOrderRequest ) throws IOException {
        GHNApiCaller apiCaller = new GHNApiCaller();
        Gson gson = new Gson();
        String json = gson.toJson(GHNCreateOrderRequest);
        String response =  apiCaller.createOrder(json);

        return response;
    }

}
