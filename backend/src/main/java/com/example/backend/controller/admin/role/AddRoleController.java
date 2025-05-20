package com.example.backend.controller.admin.role;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.ERole;
import com.example.backend.model.Role;
import com.example.backend.model.RolePermission;
import com.example.backend.service.RolePermissionService;
import com.example.backend.service.RoleService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AddRoleController", value = "/admin/role")
public class AddRoleController extends HttpServlet {
    RoleService roleService = new RoleService(DBConnection.getJdbi());
    RolePermissionService rolePermissionService = new RolePermissionService(DBConnection.getJdbi());


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader = request.getReader();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        String roleName = jsonObject.getString("roleName");
        String description = jsonObject.getString("description");

        JSONArray jsonArray = jsonObject.getJSONArray("permissionIds");
        List<Integer> permissionIds = jsonArray.toList().stream().map(o -> Integer.parseInt(o.toString())).toList();

        Role role = new Role(null, ERole.CUSTOM, roleName, description, true);

        int roleId = roleService.addRole(role);

        List<RolePermission> rolePermissions = new ArrayList<>();

        for (Integer permissionId : permissionIds ) {
            RolePermission rolePermission = new RolePermission(null, roleId, permissionId);
            rolePermissions.add(rolePermission);
        }

        rolePermissionService.addRolePermission(rolePermissions);

        request.setAttribute("roles", roleService.getAllRoles());

    }

}
