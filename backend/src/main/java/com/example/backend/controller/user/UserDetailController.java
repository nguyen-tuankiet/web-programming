package com.example.backend.controller.user;

import com.example.backend.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UserDetailController", value = "/profile")
public class UserDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
//       Integer userId = Integer.parseInt((String) session.getAttribute("userId"));

        request.getRequestDispatcher("user/user-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST ở đây
    }
}
