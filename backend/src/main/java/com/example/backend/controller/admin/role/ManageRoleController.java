package com.example.backend.controller.admin.role;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Permission;
import com.example.backend.model.Role;
import com.example.backend.service.PermissionService;
import com.example.backend.service.RoleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageRoleController", urlPatterns = {"/admin/manage-role"})
public class ManageRoleController extends HttpServlet {
    RoleService roleService = new RoleService(DBConnection.getJdbi());
    PermissionService permissionService = new PermissionService(DBConnection.getJdbi());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Role> roles = roleService.getAllRoles();
        List<Permission> permissions = permissionService.getAllPermissions();


        request.setAttribute("roles", roles);
        request.setAttribute("permissions", permissions);
        request.getRequestDispatcher("/admin/ManageRole.jsp")
                .forward(request, response);
    }
}
