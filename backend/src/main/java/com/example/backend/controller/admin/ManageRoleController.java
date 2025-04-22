package com.example.backend.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ManageRoleController", urlPatterns = {"/admin/manage-role"})
public class ManageRoleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Forward request tới JSP
        req.getRequestDispatcher("/admin/ManageRole.jsp")
                .forward(req, resp);
    }
}
