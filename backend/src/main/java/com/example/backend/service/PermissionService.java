package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.PermissionDAO;
import com.example.backend.model.Permission;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class PermissionService {
    private final PermissionDAO permissionDAO;

    public PermissionService(Jdbi jdbi) {
        this.permissionDAO = jdbi.onDemand(PermissionDAO.class);
    }

    public List<Permission> getAllPermissions() {
        return permissionDAO.getAllPermissions();
    }

    public static void main(String[] args) {
        PermissionService permissionService = new PermissionService(DBConnection.getJdbi());
        List<Permission> permissions = permissionService.getAllPermissions();
        System.out.println(permissions);
    }

}
