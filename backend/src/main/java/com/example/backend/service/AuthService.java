package com.example.backend.service;

import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.User;
import com.example.backend.util.HashUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jdbi.v3.core.Jdbi;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class AuthService {
    private UserDao userDAO;
    private EmailService emailService;
    String facebookId = null;

    public AuthService(Jdbi jdbi) {
        this.userDAO = jdbi.onDemand(UserDao.class);
        this.emailService = new EmailService();
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

        // Tạo confirmation token
        String confirmationToken = UUID.randomUUID().toString();

        // Tạo user mới và lưu thông tin
        String userId = userDAO.createUser(firstName, displayName, email, hashedPassword, salt, confirmationToken, facebookId);
        
        if (userId != null) {
            // Gửi email xác nhận
            String confirmationLink = "http://localhost:8080/backend_war/confirm?token=" + confirmationToken;
            String emailContent = "Xin chào " + firstName + ",\n\n" +
                    "Cảm ơn bạn đã đăng ký tài khoản. Vui lòng nhấp vào liên kết sau để xác nhận tài khoản của bạn:\n" +
                    confirmationLink + "\n\n" +
                    "Trân trọng,\n" +
                    "Đội ngũ hỗ trợ";
            
            emailService.sendEmail(email, "Xác nhận tài khoản", emailContent);
            return true;
        }
        return false;
    }
    public boolean registerWithGoogle(String firstName, String displayName, String email, String password) {
        if (userDAO.getUserByEmail(email) != null) {
            return false; // Email đã tồn tại
        }

        // Tạo salt ngẫu nhiên
        String salt = HashUtils.generateSalt();

        // Mã hóa mật khẩu với salt
        String hashedPassword = HashUtils.hashWithSalt(password, salt);
        String confirmationToken = UUID.randomUUID().toString();
        // Tạo user mới và lưu thông tin
        String userId = userDAO.createUser(firstName, displayName, email, hashedPassword, salt, confirmationToken, facebookId);
        return userId != null;
    }

    public boolean confirmAccount(String token) {
        User user = userDAO.getUserByConfirmationToken(token);
        if (user != null && "PENDING".equals(user.getStatus())) {
            userDAO.updateUserStatusByToken(token, "ACTIVE");
            return true;
        }
        return false;
    }

    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email.trim());
        if (user != null) {
            // Kiểm tra trạng thái tài khoản
            if ("PENDING".equals(user.getStatus())) {
                throw new RuntimeException("Tài khoản chưa được xác nhận. Vui lòng kiểm tra email của bạn.");
            }
            if ("BANNED".equals(user.getStatus())) {
                throw new RuntimeException("Tài khoản của bạn đã bị khóa.");
            }
            if ("DEACTIVE".equals(user.getStatus())) {
                throw new RuntimeException("Tài khoản của bạn đã bị vô hiệu hóa.");
            }

            String storedSalt = user.getSalt();
            String storedHashedPassword = user.getPassword();

            String hashedPassword = HashUtils.hashWithSalt(password.trim(), storedSalt);

            if (hashedPassword.equals(storedHashedPassword)) {
                return user;
            }
        }
        return null;
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

