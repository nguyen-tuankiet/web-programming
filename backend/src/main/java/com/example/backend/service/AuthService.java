package com.example.backend.service;

import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.User;
import com.example.backend.util.MD5Utils;
import org.jdbi.v3.core.Jdbi;

import java.security.NoSuchAlgorithmException;

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

    public User login(String email, String password) throws NoSuchAlgorithmException, NoSuchAlgorithmException {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            String storedHashedPassword = user.getPassword();


            if (MD5Utils.verify(password, storedHashedPassword)) {
                System.out.println("Password match!");

                return user;
            } else {
                System.out.println("Password does not match.");
            }
        }
        return null; // Nếu người dùng không tồn tại hoặc mật khẩu không khớp
    }


}

