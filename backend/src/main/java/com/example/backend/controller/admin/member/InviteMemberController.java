package com.example.backend.controller.admin.member;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.Status;
import com.example.backend.model.Invite;
import com.example.backend.service.InviteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.Duration;

//@WebServlet(name = "InviteMemberController", value = "/admin/member/invite")
@WebServlet(name = "InviteMemberController", value = "/member/invite")
public class InviteMemberController  extends HttpServlet {

    InviteService inviteService = new InviteService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader = request.getReader();
        JSONObject jsonResponse= new JSONObject();




        while ((line = bufferedReader.readLine() ) != null) {
            stringBuilder.append(line);
        }
        JSONObject jsonRequest= new JSONObject(stringBuilder.toString());
        String email = jsonRequest.getString("email");
        String name = jsonRequest.getString("name");
        int roleId = Integer.parseInt(jsonRequest.getString("roleId"));

        if (email == null || name == null || roleId == 0){
            jsonResponse.put("success", false);
        }

        Invite invite = new Invite(null, email, name, roleId,
                Status.PENDING,
                System.currentTimeMillis() + Duration.ofDays(1).toMillis(),
                System.currentTimeMillis());

        Integer inviteId = inviteService.create(invite);
        if (inviteId != null) {
            jsonResponse.put("success", true);

            ObjectMapper mapper = new ObjectMapper();
            String inviteJson = mapper.writeValueAsString(invite);
            jsonResponse.put("data", new JSONObject(inviteJson));

        }

        // TODO: Nếu chưa có tài khoản => tạo tài khoản phải set role rồi gởi mk kèm theo email confirm

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonResponse.toString());
    }


}
