package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.model.DAO.AddressDAO;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CustomerListController", value = "/admin/customers")
public class CustomerListController extends HttpServlet {

    private UserService userService;
    private AddressDAO addressDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService(DBConnection.getJdbi());
        this.addressDAO = DBConnection.getJdbi().onDemand(AddressDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("name");

        List<User> allUsers = userService.getAllUsers();
        List<User> matched = new java.util.ArrayList<>();
        List<User> others = new java.util.ArrayList<>();

        for (User user : allUsers) {
            if (user.getFullName() != null && keyword != null && !keyword.isBlank()
                    && user.getFullName().toLowerCase().contains(keyword.toLowerCase())) {
                matched.add(user);
            } else {
                others.add(user);
            }
        }

        matched.addAll(others);
        Map<Integer, String> userAddresses = new HashMap<>();

        for (User c : matched) {
            Address address = addressDAO.getAddressByUserId(c.getId()).stream().findFirst().orElse(null);
            userAddresses.put(c.getId(), address != null ? address.getProvince() : "N/A");

            if (c.getAvatarId() != null) {
                String avatarUrl = userService.getAvatarUrlById(c.getAvatarId());
                c.setAvatarUrl(avatarUrl); // Set avatar URL to User object
            }
        }

        request.setAttribute("userAddresses", userAddresses);
        request.setAttribute("customers", matched);


        request.getRequestDispatcher("customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
