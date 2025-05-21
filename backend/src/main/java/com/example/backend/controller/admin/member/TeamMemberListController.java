package com.example.backend.controller.admin.member;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Member;
import com.example.backend.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeamMemberListController", urlPatterns = {"/admin/team-member"})
public class TeamMemberListController extends HttpServlet {

    private final MemberService memberService= new MemberService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Member> members = memberService.getAll();
        request.setAttribute("teamMembers", members);
        request.getRequestDispatcher("/admin/Member.jsp").forward(request, response);
//        try {
//            List<Member> members = memberService.getAll();
//            System.out.println("Số lượng thành viên: " + members.size());
//            for (Member m : members) {
//                System.out.println("Thành viên: " + m.getFullName() + " | Status: " + m.getStatus());
//            }
//            request.setAttribute("teamMembers", members);
//            request.getRequestDispatcher("/admin/Member.jsp").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(500, "Không thể tải danh sách thành viên.");
//        }
    }

}
