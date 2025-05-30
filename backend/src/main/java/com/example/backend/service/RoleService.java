package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.ERole;
import com.example.backend.model.DAO.RoleDAO;
import com.example.backend.model.Role;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class RoleService {
    private final RoleDAO roleDAO;

    public RoleService(Jdbi jdbi) {
        roleDAO = jdbi.onDemand(RoleDAO.class);
    }

    public List<Role> getAllRoles() {
        return roleDAO.getRoles();
    }

    public int addRole(Role role ) {
        return roleDAO.addRole(role);
    }







    public static void main(String[] args) {
        RoleService roleService = new RoleService(DBConnection.getJdbi());
        Role  role = new Role(null, ERole.CUSTOM, "test2 ", "", true);

        System.out.println(roleService.getAllRoles());
    }
}
