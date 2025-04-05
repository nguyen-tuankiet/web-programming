package com.example.backend.service;

import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.User;
import com.example.backend.util.HashUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jdbi.v3.core.Jdbi;

import java.security.NoSuchAlgorithmException;

public class AuthService {
    private UserDao userDAO;

    public AuthService(Jdbi jdbi) {
        this.userDAO = jdbi.onDemand(UserDao.class);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public boolean register(String firstName, String displayName, String email, String password) {
        if (userDAO.getUserByEmail(email) != null) {
            return false; // Email đã tồn tại
        }

        // Tạo salt ngẫu nhiên
        String salt = HashUtils.generateSalt();

        // Mã hóa mật khẩu với salt
        String hashedPassword = HashUtils.hashWithSalt(password, salt);

        // Tạo user mới và lưu thông tin
        String userId = userDAO.createUser(firstName, displayName, email, hashedPassword, salt);
        return userId != null;
    }

    public boolean registerWithGoogle(String firstName, String displayName, String email, String password) {
        if (userDAO.getUserByEmail(email) != null) {
            return false; // Email đã tồn tại
        }

        // Tạo salt ngẫu nhiên
        String salt = HashUtils.generateSalt();

        // Mã hóa mật khẩu với salt
        String hashedPassword = HashUtils.hashWithSalt(password, salt);

        // Tạo user mới và lưu thông tin
        String userId = userDAO.createUser(firstName, displayName, email, hashedPassword, salt);
        return userId != null;
    }

    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email.trim());
        if (user != null) {
            String storedSalt = user.getSalt(); // Lấy salt từ cơ sở dữ liệu
            String storedHashedPassword = user.getPassword();

            // Mã hóa mật khẩu nhập vào với salt, sau khi đã loại bỏ khoảng trắng
            String hashedPassword = HashUtils.hashWithSalt(password.trim(), storedSalt);

            if (hashedPassword.equals(storedHashedPassword)) {
                return user; // Mật khẩu đúng
            }
        }
        return null; // Mật khẩu không đúng hoặc user không tồn tại
    }

    public boolean changePassword(Integer userId, String oldPassword, String newPassword, boolean verifyOldPassword) {
        User user = userDAO.getPasswordByUserId(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (verifyOldPassword) {
            String storedSalt = user.getSalt();
            String storedHashedPassword = user.getPassword();

            String hashedPassword = HashUtils.hashWithSalt(oldPassword, storedSalt);

            if (!hashedPassword.equals(storedHashedPassword)) {
                throw new IllegalArgumentException("Current password is incorrect");
            }
        }

        String newSalt = HashUtils.generateSalt();
        String hashedNewPassword = HashUtils.hashWithSalt(newPassword, newSalt);

        return userDAO.updatePassword(userId, hashedNewPassword, newSalt) > 0;
    }

    public User getUserById(Integer userId) {
        return userDAO.getUserById(userId);
    }

    public boolean verifySession(HttpServletRequest request, String sessionId) {
        HttpSession session = request.getSession(false);  // Lấy session hiện tại, nếu không có thì trả về null
        if (session != null) {
            String storedSessionId = (String) session.getAttribute("sessionId");
            return storedSessionId != null && storedSessionId.equals(sessionId);
        }
        return false;
    }

    public void activateUserAccount(HttpServletRequest request, String sessionId) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String storedSessionId = (String) session.getAttribute("sessionId");
            if (storedSessionId != null && storedSessionId.equals(sessionId)) {
                String email = (String) session.getAttribute("email");

                // Thông báo tài khoản đã được xác nhận
                System.out.println("Tài khoản với email " + email + " đã được xác nhận.");
//                sendAccountActivationEmail(email);
            }
        }
    }

    public void saveSessionId(HttpServletRequest request, String email, String sessionId) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionId", sessionId);
        session.setAttribute("email", email);  // Lưu email vào session nếu cần thiết
    }
}

