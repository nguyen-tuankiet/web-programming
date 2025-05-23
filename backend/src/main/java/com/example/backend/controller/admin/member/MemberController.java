package com.example.backend.controller.admin.member;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Member;
import com.example.backend.model.Role;
import com.example.backend.service.MemberService;

import com.example.backend.service.RoleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeamMemberListController", urlPatterns = {"/admin/team-member"})
public class MemberController extends HttpServlet {
    private final RoleService roleService = new RoleService(DBConnection.getJdbi());
    private final MemberService memberService= new MemberService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Member> members = memberService.getAll();
        List<Role> roles = roleService.getAllRoles();



        request.setAttribute("teamMembers", members);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/admin/Member.jsp").forward(request, response);




    }

}
