package com.example.backend.controller.user.profile;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UpdateUserInfor", value = "/updateUser")
public class UpdateUserInforController extends HttpServlet {
    UserService userService = new UserService(DBConnection.getJdbi());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);

        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> requestData = objectMapper.readValue(stringBuilder.toString(), Map.class);
        HttpSession session = request.getSession();



        Integer userId = (Integer) session.getAttribute("userId");
        User user = new User();
        user.setId(userId);
        user.setFullName(requestData.get("fullName").toString());
        user.setDisplayName(requestData.get("displayName").toString());
        user.setGender(requestData.get("gender").toString());
//        user.setBirth(LocalDate.parse(requestData.get("birth").toString()));
//        user.setEmail(requestData.get("email").toString());
        user.setPhone(requestData.get("phone").toString());

        Boolean success = userService.updateUser(user);




        JSONObject jsonResponse = new JSONObject();

        if (success){
            response.setStatus(HttpServletResponse.SC_OK);
            jsonResponse.put("success", true);
            jsonResponse.put("message", "User updated successfully!");
        }
        else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Update user failed!");
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
