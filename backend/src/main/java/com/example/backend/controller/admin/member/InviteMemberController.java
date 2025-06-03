package com.example.backend.controller.admin.member;

import com.example.backend.Connection.DBConnection;
import com.example.backend.config.ConfigLoader;
import com.example.backend.contant.EPermission;
import com.example.backend.contant.Status;
import com.example.backend.model.Invite;
import com.example.backend.model.User;
import com.example.backend.service.AuthService;
import com.example.backend.service.EmailService;
import com.example.backend.service.InviteService;
import com.example.backend.util.CustomResponse;
import com.example.backend.util.ValidationPermissionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.List;

@WebServlet(name = "InviteMemberController", value = "/admin/member/invite")
public class InviteMemberController extends HttpServlet {
    EmailService emailService = new EmailService();
    InviteService inviteService = new InviteService(DBConnection.getJdbi());
    AuthService authService = new AuthService(DBConnection.getJdbi());
//    private final String host = ConfigLoader.get("host.dev");
    private final String host = ConfigLoader.get("host.product");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader = request.getReader();
        JSONObject jsonResponse = new JSONObject();



        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JSONObject jsonRequest = new JSONObject(stringBuilder.toString());
        String email = jsonRequest.getString("email");
        String name = jsonRequest.getString("name");
        String roleName = jsonRequest.getString("roleName");
        int roleId = Integer.parseInt(jsonRequest.getString("roleId"));

        if (email == null || name == null || roleId == 0) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Missing required fields");
            sendResponse(response, jsonResponse);
            return;
        }

        try {
            // Kiểm tra xem user đã có tài khoản chưa
            User existingUser = authService.getUserByEmail(email);

            if (existingUser == null) {
                // Case: User chưa có tài khoản => Tạo tài khoản mới với role được chỉ định
                handleNewUserInvite(email, name, roleId, roleName, jsonResponse);
            } else {
                // Case: User đã có tài khoản => Chỉ gửi invite link như cũ
                handleExistingUserInvite(email, name, roleId, roleName, jsonResponse);
            }

        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error processing invite: " + e.getMessage());
            e.printStackTrace();
        }

        sendResponse(response, jsonResponse);
    }

    /**
     * Xử lý invite cho user chưa có tài khoản
     * Tạo tài khoản mới với role được chỉ định và gửi email với mật khẩu tạm thời
     */
    private void handleNewUserInvite(String email, String name, int roleId, String roleName, JSONObject jsonResponse) {
        try {
            // Tạo mật khẩu tạm thời
            String temporaryPassword = generateTemporaryPassword();

            // Tạo tài khoản mới với role được chỉ định
            boolean accountCreated = authService.registerWithRole(name, name, email, temporaryPassword, roleId);

            if (accountCreated) {
                String urlLogin = host + "/login";
                // Gửi email chứa thông tin tài khoản và mật khẩu tạm thời
                emailService.sendAccountCreationEmailPlain(email, name, roleName, temporaryPassword, urlLogin);

                jsonResponse.put("success", true);
                jsonResponse.put("message", "Account created successfully and invitation email sent");
                jsonResponse.put("data", new JSONObject()
                        .put("email", email)
                        .put("name", name)
                        .put("roleName", roleName)
                        .put("accountCreated", true));
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Failed to create account");
            }

        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error creating account: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Xử lý invite cho user đã có tài khoản (logic cũ)
     */
    private void handleExistingUserInvite(String email, String name, int roleId, String roleName, JSONObject jsonResponse) {
        try {
            Invite invite = new Invite(null, email, name, roleId,
                    Status.PENDING,
                    System.currentTimeMillis() + Duration.ofDays(1).toMillis(),
                    System.currentTimeMillis());

            Integer inviteId = inviteService.create(invite);

            if (inviteId != null) {
                String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);
                String url = host + "/accept-invite?id=" + inviteId + "&email=" + encodedEmail;
                emailService.sendInviteEmail(email, name, roleName, url);

                jsonResponse.put("success", true);
                jsonResponse.put("message", "Invitation sent successfully");

                ObjectMapper mapper = new ObjectMapper();
                String inviteJson = mapper.writeValueAsString(invite);
                jsonResponse.put("data", new JSONObject(inviteJson)
                        .put("accountCreated", false));
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Failed to create invitation");
            }

        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error sending invitation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Tạo mật khẩu tạm thời ngẫu nhiên
     */
    private String generateTemporaryPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        return password.toString();
    }




    private void sendResponse(HttpServletResponse response, JSONObject jsonResponse) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonResponse.toString());
    }
}