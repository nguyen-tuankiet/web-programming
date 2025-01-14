package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.User;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDate;
import java.util.List;

public class UserService {
    UserDao userDao;

    public UserService(Jdbi jdbi) {
        this.userDao= jdbi.onDemand(UserDao.class);
    }


    public User getUserById(Integer id) {
        User user = userDao.getUserById(id);
        return user;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    public Boolean updateAvatar(Integer userId, Integer avatarId) {
        return userDao.updateAvatar(userId, avatarId);
    }


    public Boolean updateUser(User user) {
        return userDao.updateUser(
                user.getId(),
                user.getFullName(),
                user.getDisplayName(),
                user.getBirth(),
                user.getGender(),
                user.getPhone(),
                user.getEmail()
        );
    }

    public static void main(String[] args) {
        UserService userService = new UserService(DBConnection.getJdbi());
        User user =new User(
                1, "qhung", "qhung",
                LocalDate.now(), "male","hun@gmad.ee", "232232222", null, null, null, null, null

        );

        System.out.println(userService.updateUser(user));
    }

}
