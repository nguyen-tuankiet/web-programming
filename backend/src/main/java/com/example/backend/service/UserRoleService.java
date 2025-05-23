package com.example.backend.service;

import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.DAO.UserRoleDAO;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.customizer.Bind;

public class UserRoleService {
    UserRoleDAO userRoleDAO;
    public UserRoleService(Jdbi jdbi) {
        this.userRoleDAO = jdbi.onDemand(UserRoleDAO.class);
    }

    public Integer addUserRole(@Bind("userId") Integer userId, @Bind("roleId") Integer roleId) {
        return userRoleDAO.addUserRole(userId, roleId);
    }

    public Boolean updateUserRole(@Bind("userId") Integer userId, @Bind("roleId") Integer roleId) {
        return userRoleDAO.updateUserRole(userId, roleId);
    }

    public static void main(String[] args) {

    }

}
