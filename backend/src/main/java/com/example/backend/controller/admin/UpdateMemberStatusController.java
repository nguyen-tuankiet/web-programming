package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.MemberService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/team-member/update-status")
public class UpdateMemberStatusController extends HttpServlet {

    private final MemberService service = new MemberService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String status = request.getParameter("status");
        service.updateStatus(memberId, status);
        response.sendRedirect(request.getContextPath() + "/admin/team-member");
    }
}
