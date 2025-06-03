package com.example.backend.controller.user.address;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "FindUserAddressController", value = "/address")
public class FindUserAddressController extends HttpServlet {
    private AddressService addressService = new AddressService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonResponse = new JSONObject();
        Integer userId = Integer.parseInt(request.getParameter("userId"));

        List<Address> addresses = addressService.findByUserId(userId);

        jsonResponse.put("success", true);
        jsonResponse.put("data", addresses);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonResponse.toString());
    }
}
