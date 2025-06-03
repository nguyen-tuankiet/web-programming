package com.example.backend.controller.admin.member;


import com.example.backend.Connection.DBConnection;
import com.example.backend.service.UserRoleService;
import com.example.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ChangeMemberRoleController", urlPatterns = {"/admin/member/change-role"})
public class ChangeMemberRoleController extends HttpServlet {

    private final UserRoleService userRoleService = new UserRoleService(DBConnection.getJdbi());
    private final UserService userService = new UserService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        TODO: Check phân quyền - MANAGE_MEMBER

        StringBuilder stringBuilder = new StringBuilder();
        String line ;
        BufferedReader bufferedReader = request.getReader();
        JSONObject jsonResponse = new JSONObject();


        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JSONObject jsonRequest = new JSONObject(stringBuilder.toString());
        Integer memberId = Integer.parseInt(jsonRequest.getString("memberId"));
        Integer roleId = Integer.parseInt(jsonRequest.getString("roleId"));

        Boolean isSuccess = userRoleService.updateUserRole(memberId, roleId);
        if (isSuccess) {
            userService.updateNeedRefresh(memberId, true);
            jsonResponse.put("success", true);
        }
        else {
            jsonResponse.put("success", false);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());

    }
}
