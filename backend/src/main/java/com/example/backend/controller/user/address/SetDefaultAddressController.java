package com.example.backend.controller.user.address;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Address;
import com.example.backend.service.AddressService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "SetDefaultAddressController", value = "/address/default")
public class SetDefaultAddressController extends HttpServlet {
    AddressService addressService = new AddressService(DBConnection.getJdbi());


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("User not logged in");
        }




        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        JSONObject jsonObject = new JSONObject(json.toString());


        int addressId = jsonObject.getInt("addressId");
        Address address = addressService.findById(addressId);
        if (address == null || !address.getUserId().equals(userId)) {
            throw new RuntimeException("Address not found");
        }



        Address addressDefault = addressService.findDefautlByUserId(userId);
        if (addressDefault != null) {
            addressDefault.setIsDefault(false);
            addressService.updateDefautlById(addressDefault.getId(), false);
        }

        boolean success=  addressService.updateDefautlById(address.getId(), true);

        if (success) {
            response.getWriter().write("{\"status\":\"success\", \"message\":\"Address updated successfully\"}");
        }
        else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"Update failed\"}");

        }


    }
}
