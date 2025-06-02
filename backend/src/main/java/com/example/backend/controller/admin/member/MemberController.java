package com.example.backend.controller.admin.member;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Member;
import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.service.MemberService;

import com.example.backend.service.RoleService;
import com.example.backend.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeamMemberListController", urlPatterns = {"/admin/team-member"})
public class MemberController extends HttpServlet {
    private final RoleService roleService = new RoleService(DBConnection.getJdbi());
    private final MemberService memberService= new MemberService(DBConnection.getJdbi());
    private final UserService userService = new UserService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> members = userService.getMembers();
        List<>

        request.setAttribute("members", members);

        request.getRequestDispatcher("/admin/Member.jsp").forward(request, response);




    }

}
