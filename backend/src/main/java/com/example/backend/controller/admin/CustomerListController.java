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
        List<User> users = userService.getAllUsers();
        Map<Integer, String> userAddresses = new HashMap<>();

        for (User user : users) {
            Address address = addressDAO.getAddressByUserId(user.getId()).stream().findFirst().orElse(null);
            userAddresses.put(user.getId(), address != null ? address.getProvince() : "N/A");

            if (user.getAvatarId() != null) {
                String avatarUrl = userService.getAvatarUrlById(user.getAvatarId());
                user.setAvatarUrl(avatarUrl); // Set avatar URL to User object
            } else {
                user.setAvatarUrl("default-avatar-url.jpg"); // Default avatar URL
            }
        }

        request.setAttribute("users", users);
        request.setAttribute("userAddresses", userAddresses);


        request.getRequestDispatcher("customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
