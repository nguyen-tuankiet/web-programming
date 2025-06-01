package com.example.backend.controller.admin.customer;

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
        List<User> customers = userService.getAllUsers();
        Map<Integer, String> userAddresses = new HashMap<>();

        for (User c : customers) {
            Address address = addressDAO.getAddressByUserId(c.getId()).stream().findFirst().orElse(null);
            userAddresses.put(c.getId(), address != null ? address.getProvince() : "N/A");

            if (c.getAvatarId() != null) {
                String avatarUrl = userService.getAvatarUrlById(c.getAvatarId());
                c.setAvatarUrl(avatarUrl); // Set avatar URL to User object
            }
//            else {
//                user.setAvatarUrl( request.getContextPath() + "/assets/images/default-avatar.png");
//                // Default avatar URL
//            }
        }

        request.setAttribute("customers", customers);
        request.setAttribute("userAddresses", userAddresses);


        request.getRequestDispatcher("customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
