package com.example.backend.controller.admin.role;

import com.example.backend.Connection.DBConnection;
import com.example.backend.service.RoleService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "GetRoleController", value = "/admin/role")
public class AddRoleController extends HttpServlet {
    RoleService roleService = new RoleService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){


        request.setAttribute("roles", roleService.getAllRoles());

    }

}
