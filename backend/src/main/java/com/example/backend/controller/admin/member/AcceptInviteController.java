package com.example.backend.controller.admin.member;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.Status;
import com.example.backend.model.Invite;
import com.example.backend.model.User;
import com.example.backend.model.UserRole;
import com.example.backend.service.InviteService;
import com.example.backend.service.RoleService;
import com.example.backend.service.UserRoleService;
import com.example.backend.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AcceptInviteController", value = "/accept-invite")
public class AcceptInviteController extends HttpServlet {
    private final InviteService inviteService = new InviteService(DBConnection.getJdbi());
    private final UserService userService = new UserService(DBConnection.getJdbi());
    private final UserRoleService userRoleService = new UserRoleService(DBConnection.getJdbi());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String idString = request.getParameter("id");
        String email = request.getParameter("email");
        if (idString == null || email == null) {
            response.sendRedirect("login");
            return;
        }

        Integer inviteId = Integer.parseInt(idString);
        Invite invite = inviteService.getInviteByIdAndEmail(inviteId, email);
        if (invite == null) {
            response.sendRedirect("login");
            return;
        }
        if (invite.getExpiresAt() < System.currentTimeMillis()) {
            response.sendRedirect("expired-invite.jsp");
            return;
        }

        User user = userService.getUserByEmail(email);
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        Boolean isSuccess= inviteService.updateInviteStatus(invite.getId() , Status.ACCEPTED);



        if (isSuccess) {
            if ( userRoleService.updateUserRole(user.getId(), invite.getRoleId() )){
                response.sendRedirect("accept-success.jsp");
                return;
            }

        }












    }
}
