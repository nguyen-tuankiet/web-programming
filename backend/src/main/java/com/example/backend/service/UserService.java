package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.User;
import org.jdbi.v3.core.Jdbi;

public class UserService {
    UserDao userDao;

    public UserService(Jdbi jdbi) {
        this.userDao= jdbi.onDemand(UserDao.class);
    }


    public User getUserById(Integer id) {
        User user = userDao.getUserById(id);
        return user;
    }


    public Boolean updateAvatar(Integer userId, Integer avatarId) {
        return userDao.updateAvatar(userId, avatarId);
    }


    public static void main(String[] args) {
        UserService userService = new UserService(DBConnection.getJdbi());
        User user = userService.getUserById(111);
        System.out.println(userService.updateAvatar(user.getId(), 1));
    }

}
