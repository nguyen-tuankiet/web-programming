package com.example.backend.controller.admin.member;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Invite;
import com.example.backend.service.InviteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AcceptInviteController", value = "/invite/accept")
public class AcceptInviteController extends HttpServlet {
    private final InviteService inviteService = new InviteService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String idString = request.getParameter("id");
//        String email = request.getParameter("email");
//        if (idString == null || email == null) {
//            response.sendRedirect("/login");
//        }
//        assert idString != null;
//        Integer id = Integer.parseInt(idString);
//
//        Invite invite = inviteService.getInviteByIdAndEmail(id, email);
//
//        if (invite == null) {
//            response.sendRedirect("/login");
//        }
//

        response.sendRedirect("/other/expired-invite.jsp");








    }
}
