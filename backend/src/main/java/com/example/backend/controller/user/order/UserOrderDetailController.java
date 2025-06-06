package com.example.backend.controller.user.order;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.*;
import com.example.backend.service.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserOrderDetailController", value = "/user-order-detail")
public class UserOrderDetailController extends HttpServlet {

    OrderDetailService orderDetailService = new OrderDetailService(DBConnection.getJdbi());
    OrderSerivce orderSerivce = new OrderSerivce(DBConnection.getJdbi());
    UserService userService = new UserService(DBConnection.getJdbi());
    CardService cardService = new CardService(DBConnection.getJdbi());
    AddressService addressService = new AddressService(DBConnection.getJdbi());
    ReviewService reviewService = new ReviewService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        HttpSession session = request.getSession();
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());

        User user = userService.getUserById(userId);
        if (user != null) {
            request.setAttribute("user", user);
        }

        Order order = orderSerivce.getOrderByIdAndUserId(orderId,userId);
       if (order != null) {
           request.setAttribute("order", order);

           if (!order.getIsCOD()){
               Card card = cardService.getCardById(order.getCardId());
               if (card != null) {
                   request.setAttribute("card", card);
               }
           }


           Address address = addressService.findById(order.getAddressId());
           if (address != null) {
               request.setAttribute("address", address);
           }

           request.setAttribute("COD", order.getIsCOD());
       }

        List<OrderDetail> orderDetails = null;
        if (order != null && order.getId() != null) {
            orderDetails = orderDetailService.getOrderDetailByOrderId(orderId);
            request.setAttribute("orderDetails", orderDetails);
        }

        Map<Integer, Review> reviewMap = new HashMap<>();
        boolean allReviewed = true;
        if (orderDetails != null) {
            for (OrderDetail od : orderDetails) {
                Review review = reviewService.getReview(userId, orderId, od.getProductId());
                if (review != null) {
                    reviewMap.put(od.getProductId(), review);
                }
            }
        }
        request.setAttribute("reviewMap", reviewMap);
        request.setAttribute("allReviewed", allReviewed);


        request.getRequestDispatcher("user/user-order-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
