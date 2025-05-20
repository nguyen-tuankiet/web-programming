package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.RolePermissionDAO;
import com.example.backend.model.RolePermission;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.customizer.BindBean;

import java.util.List;

public class RolePermissionService {
    private final RolePermissionDAO rolePermissionDAO;

    public RolePermissionService(Jdbi jdbi) {
        this.rolePermissionDAO = jdbi.onDemand(RolePermissionDAO.class);
    }


    public void addRolePermission( List<RolePermission> rolePermissions) {
        rolePermissionDAO.addRolePermissions(rolePermissions);
    }


    public static void main(String[] args) {
        RolePermission rolePermission1 = new RolePermission(null, 11, 2);
        RolePermission rolePermission2 = new RolePermission(null, 11, 3);
        RolePermission rolePermission3 = new RolePermission(null, 11, 4);
        RolePermission rolePermission4 = new RolePermission(null, 11, 5);

        RolePermissionService service = new RolePermissionService(DBConnection.getJdbi());
        service.addRolePermission(List.of(rolePermission1, rolePermission2, rolePermission3, rolePermission4));
    }
}
