package com.example.backend.service;


import com.example.backend.config.ConfigLoader;
import com.example.backend.model.DAO.PermissionDAO;
import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.DAO.UserRoleDAO;
import com.example.backend.model.Permission;
import com.example.backend.model.User;
import com.example.backend.model.Role;
import com.example.backend.util.HashUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jdbi.v3.core.Jdbi;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public class AuthService {
    private UserDao userDAO;
    private UserRoleDAO userRoleDAO;
    private EmailService emailService;
    String facebookId = null;
    private PermissionDAO permissionDAO;


    public AuthService(Jdbi jdbi) {
        this.userDAO = jdbi.onDemand(UserDao.class);
        this.userRoleDAO = jdbi.onDemand(UserRoleDAO.class);
        this.permissionDAO = jdbi.onDemand(PermissionDAO.class);
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
        Integer userId = userDAO.createUser(firstName, displayName, email, hashedPassword, salt, confirmationToken, facebookId);

        if (userId != null) {
            // Lấy role mặc định cho user (USER role)
            Role defaultRole = userDAO.getDefaultUserRole();
            if (defaultRole != null) {
                // Thêm role vào bảng user_role
                userRoleDAO.addUserRole(userId, defaultRole.getId());
            }

            // Gửi email xác nhận
            String hostProduct = ConfigLoader.get("host.product");

            String confirmationLink = hostProduct + "/confirm?token=" + confirmationToken;
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
        Integer userId = userDAO.createUser(firstName, displayName, email, hashedPassword, salt, confirmationToken, facebookId);

        if (userId != null) {
            // Lấy role mặc định cho user (USER role)
            Role defaultRole = userDAO.getDefaultUserRole();
            if (defaultRole != null) {
                // Thêm role vào bảng user_role
                userRoleDAO.addUserRole(userId, defaultRole.getId());
            }
            return true;
        }
        return false;
    }

    public boolean registerWithRole(String firstName, String displayName, String email, String password, Integer roleId) {
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
        Integer userId = userDAO.createUser(firstName, displayName, email, hashedPassword, salt, confirmationToken, facebookId);

        if (userId != null) {
            // Thêm role được chỉ định vào bảng user_role
            userRoleDAO.addUserRole(userId, roleId);

            // Gửi email xác nhận
            String confirmationLink = "http://modernhome.property/confirm?token=" + confirmationToken;
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
//            if ("BANNED".equals(user.getStatus())) {
//                throw new RuntimeException("Tài khoản của bạn đã bị khóa.");
//            }
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

    public boolean changeUserRole(Integer userId, Integer newRoleId) {
        return userRoleDAO.updateUserRole(userId, newRoleId);
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

    public List<Permission> getPermissionsByRoleId(Integer roleId) {
        return permissionDAO.getPermissionsByRoleId(roleId);
    }

    public boolean registerWithGoogleActive(String firstName, String displayName, String email, String password) {
        if (userDAO.getUserByEmail(email) != null) {
            return false; // Email đã tồn tại
        }

        // Tạo salt ngẫu nhiên
        String salt = HashUtils.generateSalt();

        // Mã hóa mật khẩu với salt
        String hashedPassword = HashUtils.hashWithSalt(password, salt);
        String confirmationToken = UUID.randomUUID().toString();

        // Tạo user mới với status ACTIVE ngay lập tức cho Google OAuth
        Integer userId = userDAO.createUserWithActiveStatus(firstName, displayName, email, hashedPassword, salt, confirmationToken, facebookId);

        if (userId != null) {
            // Lấy role mặc định cho user (USER role)
            Role defaultRole = userDAO.getDefaultUserRole();
            if (defaultRole != null) {
                // Thêm role vào bảng user_role
                userRoleDAO.addUserRole(userId, defaultRole.getId());
            }
            return true;
        }
        return false;
    }

    public void activateUserAccount(Integer userId) {
        userDAO.updateUserStatus(userId, "ACTIVE");
    }
}