package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.model.Order;
import com.example.backend.model.User;
import com.example.backend.service.AddressService;
import com.example.backend.service.OrderSerivce;
import com.example.backend.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerDetailController", value = {"/admin/customer"})
public class CustomerDetailController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CustomerDetailController.class);
    private final UserService userService = new UserService(DBConnection.getJdbi());
    private final AddressService addressService = new AddressService(DBConnection.getJdbi());
    private final OrderSerivce orderService = new OrderSerivce(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("id");
        if (param == null) {
            throw new ServletException("Customer ID is required");
        }
        int id = Integer.parseInt(param);
        User user = userService.getUserById(id);
        List<Address> address = addressService.findByUserId(id);
        List<Order> order = orderService.getOrdersByUserId(id);

        log.info("Customer detail ID: " + id);
        log.info("Customer detail user: " + user);
        log.info("Customer detail address: " + address);
        log.info("Customer detail order: " + order);


        request.setAttribute("customer", user);
        request.setAttribute("address", address);
        request.setAttribute("order", order);
        request.getRequestDispatcher("customerDetail.jsp").forward(request, response);
    }


}
