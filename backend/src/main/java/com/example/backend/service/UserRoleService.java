package com.example.backend.service;

import com.example.backend.model.DAO.UserRoleDAO;
import org.jdbi.v3.core.Jdbi;

public class UserRoleService {
    UserRoleDAO userRoleDAO;
    public UserRoleService(Jdbi jdbi) {
        this.userRoleDAO = jdbi.onDemand(UserRoleDAO.class);
    }

    public Integer addUserRole( Integer userId,  Integer roleId) {
        return userRoleDAO.addUserRole(userId, roleId);
    }

    public Boolean updateUserRole(  Integer userId,  Integer roleId) {
        return userRoleDAO.updateUserRole(userId, roleId);
    }

    public static void main(String[] args) {

    }

}
