package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.config.EnvConfig;
import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.User;
import com.example.backend.util.FacebookUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jdbi.v3.core.Jdbi;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/facebook-callback")
public class FacebookCallbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String FACEBOOK_APP_ID = EnvConfig.get("FACEBOOK_APP_ID");
    private final String FACEBOOK_APP_SECRET = EnvConfig.get("FACEBOOK_APP_SECRET");
    private final String REDIRECT_URI = "http://modernhome.property/facebook-callback";

    private Jdbi jdbi;

    @Override
    public void init() throws ServletException {
        super.init();
        jdbi = DBConnection.getJdbi();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            String sessionState = (String) request.getSession().getAttribute("facebook_state");
            String fbAction = (String) request.getSession().getAttribute("fbAuthMode");

            // Debug logging
            System.out.println("Request state: " + state);
            System.out.println("Session state: " + sessionState);
            System.out.println("Facebook action: " + fbAction);

            // Kiểm tra state parameter
            if (state == null || !state.equals(sessionState)) {
                System.out.println("State mismatch - Request: " + state + ", Session: " + sessionState);
                response.sendRedirect(request.getContextPath() + "/login?error=Invalid state parameter");
                return;
            }

            if (code == null || code.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/login?error=No authorization code received");
                return;
            }

            String accessToken = FacebookUtil.getAccessToken(
                    FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, REDIRECT_URI, code);

            JSONObject fbUserData = FacebookUtil.getUserData(accessToken);

            String facebookId = fbUserData.getString("id");
            String name = fbUserData.getString("name");
            String email = fbUserData.optString("email", null);

            System.out.println("Facebook User Data - Name: " + name + ", Email: " + email + ", Action: " + fbAction);

            HttpSession session = request.getSession();

            boolean isRegistration = "register".equals(fbAction);
            System.out.println("Is Registration Mode: " + isRegistration);

            User user = jdbi.withExtension(UserDao.class, dao -> {
                if (email != null && !email.isEmpty()) {
                    User existingUser = dao.getUserByEmail(email);
                    if (existingUser != null) {
                        if (isRegistration) {
                            return null;
                        }
                        return existingUser;
                    }
                }

                if (isRegistration || email != null) {
                    String confirmationToken = UUID.randomUUID().toString();
                    String randomPassword = UUID.randomUUID().toString();
                    String salt = UUID.randomUUID().toString();
                    String userId = String.valueOf(dao.createUser(
                            name,
                            name,
                            email,
                            randomPassword,
                            salt,
                            confirmationToken,
                            facebookId
                    ));
                    dao.updateUserStatus(Integer.parseInt(userId), "ACTIVE");
                    User newUser = dao.getUserById(Integer.parseInt(userId));
                    System.out.println("New user created - ID: " + userId + ", Role: " + newUser.getRole() + 
                                     ", Email: " + newUser.getEmail() + ", FacebookId: " + newUser.getFacebookId());
                    return newUser;
                }

                return null;
            });

            if (user == null) {
                if (isRegistration) {
                    response.sendRedirect(request.getContextPath() + "/login?error=Không thể tạo tài khoản Facebook. Vui lòng thử lại.");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login?error=Tài khoản Facebook này chưa được đăng ký.");
                }
                return;
            }

            System.out.println("User before setting session - ID: " + user.getId() + ", Role: " + user.getRole());

            session.setAttribute("loggedInUser", user);
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userId", user.getId());
            session.setMaxInactiveInterval(30 * 60);

            System.out.println("Session after setting - UserRole: " + session.getAttribute("userRole"));
            System.out.println("Session after setting - UserId: " + session.getAttribute("userId"));

            // JavaScript: set sessionStorage và redirect
            String script = "<script>" +
                    "sessionStorage.setItem('role', '" + user.getRole() + "');" +
                    "sessionStorage.setItem('sessionId', '" + session.getId() + "');" +
                    "sessionStorage.setItem('userId', '" + user.getId() + "');" +
                    "console.log('Setting sessionStorage - Role: ' + sessionStorage.getItem('role'));" +
                    "console.log('Setting sessionStorage - UserId: ' + sessionStorage.getItem('userId'));" +
                    "if (sessionStorage.getItem('role') === 'ADMIN') {" +
                    "   window.location.href = '" + request.getContextPath() + "/admin/dashboard';" +
                    "} else {" +
                    "   window.location.href = '" + request.getContextPath() + "/home?success=Login successful';" +
                    "}" +
                    "</script>";

            response.setContentType("text/html");
            response.getWriter().write(script);

            // Xóa state
            request.getSession().removeAttribute("facebook_state");
            request.getSession().removeAttribute("fbAuthMode");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login?error=Facebook login failed: " + e.getMessage());
        }
    }
}
