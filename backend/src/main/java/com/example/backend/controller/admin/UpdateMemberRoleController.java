package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.MemberService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/team-member/update-role")
public class UpdateMemberRoleController extends HttpServlet {

    private final MemberService service = new MemberService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String role = request.getParameter("role");
        service.updateRole(memberId, role);
        response.sendRedirect(request.getContextPath() + "/admin/team-member");
    }
}
