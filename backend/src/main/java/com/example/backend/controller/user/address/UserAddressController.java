package com.example.backend.controller.user.address;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.model.User;
import com.example.backend.service.AddressSevice;
import com.example.backend.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserAddressController", value = "/user-address")
public class UserAddressController extends HttpServlet {
    UserService userService = new UserService(DBConnection.getJdbi());
    AddressSevice addressSevice = new AddressSevice(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            User user = userService.getUserById(userId);
            List<Address> addresses = addressSevice.findByUserId(userId);

            request.setAttribute("user", user);
            request.setAttribute("addresses", addresses);
        }

        request.getRequestDispatcher("user/user-address.jsp").forward(request, response);
    }
}
