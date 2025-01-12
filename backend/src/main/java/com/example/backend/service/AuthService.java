package com.example.backend.service;

import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.User;
import com.example.backend.util.HashUtils;
import org.jdbi.v3.core.Jdbi;

import java.security.NoSuchAlgorithmException;

public class AuthService {
    private UserDao userDAO;

    public AuthService(Jdbi jdbi) {
        this.userDAO = jdbi.onDemand(UserDao.class);
    }

    public boolean register(String fullName, String displayName, String email, String password) {
        if (userDAO.getUserByEmail(email) != null) {
            return false; // Email đã tồn tại
        }

        // Tạo salt ngẫu nhiên
        String salt = HashUtils.generateSalt();

        // Mã hóa mật khẩu với salt
        String hashedPassword = HashUtils.hashWithSalt(password, salt);

        // Tạo user mới và lưu thông tin
        String userId = userDAO.createUser(fullName, displayName, email, hashedPassword, salt);
        return userId != null;
    }


    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            String storedSalt = user.getSalt(); // Lấy salt từ cơ sở dữ liệu
            String storedHashedPassword = user.getPassword();

            // Mã hóa mật khẩu nhập vào với salt
            String hashedPassword = HashUtils.hashWithSalt(password, storedSalt);

            if (hashedPassword.equals(storedHashedPassword)) {
                return user; // Mật khẩu đúng
            }
        }
        return null; // Mật khẩu không đúng hoặc user không tồn tại
    }


    public boolean changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userDAO.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        String storedSalt = user.getSalt();

        String hashedCurrentPassword = HashUtils.hashWithSalt(oldPassword, storedSalt);
        System.out.println(hashedCurrentPassword);

        if (!hashedCurrentPassword.equals(user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        String newSalt = HashUtils.generateSalt();
        String hashedNewPassword = HashUtils.hashWithSalt(newPassword, newSalt);

        return userDAO.updatePassword(userId, hashedNewPassword, newSalt) > 0;

    }

}

