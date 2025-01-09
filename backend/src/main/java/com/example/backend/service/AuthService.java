package com.example.backend.service;

import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.User;
import com.example.backend.util.MD5Utils;
import org.jdbi.v3.core.Jdbi;

public class AuthService {
    private UserDao userDAO;

    public AuthService(Jdbi jdbi) {
        this.userDAO = jdbi.onDemand(UserDao.class);
    }

    public boolean register(String fullName, String displayName, String email, String password) {
        // Kiểm tra email đã tồn tại chưa
        if (userDAO.getUserByEmail(email) != null) {
            return false;
        }

        // Mã hóa mật khẩu
        String hashedPassword = MD5Utils.hash(password);

        // Tạo user mới
        String userId = userDAO.createUser(fullName, displayName, email, hashedPassword);
        return userId != null;
    }

    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            String hashedPassword = MD5Utils.hash(password);
            if (user.getPassword().equals(hashedPassword)) {
                return user;
            }
        }
        return null;
    }
}

