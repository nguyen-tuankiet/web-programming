package com.example.backend.controller.auth;
import com.example.backend.Connection.DBConnection;
import com.example.backend.config.ConfigLoader;
import com.example.backend.config.EnvConfig;
import com.example.backend.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/login-facebook")
public class FacebookLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String FACEBOOK_APP_ID = EnvConfig.get("FACEBOOK_APP_ID");
//    private final String REDIRECT_URI = "http://localhost:8080/backend_war/facebook-callback";
    private String redirectUri;

    @Override
    public void init() throws ServletException {
        String hostProduct = ConfigLoader.get("host.dev");
        this.redirectUri = hostProduct + "/backend_war/facebook-callback";
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy mode từ request parameter (register hoặc login)
        String mode = request.getParameter("mode");
        if (mode == null) {
            mode = "login"; // default là login nếu không có mode
        }

        // Tạo state parameter ngẫu nhiên
        String state = UUID.randomUUID().toString();
        
        // Lấy hoặc tạo session mới
        HttpSession session = request.getSession(true);
        
        // Log trước khi set session
        System.out.println("Setting session state: " + state);
        System.out.println("Setting fbAuthMode: " + mode);
        
        // Lưu state và mode vào session
        session.setAttribute("facebook_state", state);
        session.setAttribute("fbAuthMode", mode);

        // Log sau khi set session
        System.out.println("Verify session state: " + session.getAttribute("facebook_state"));
        System.out.println("Verify fbAuthMode: " + session.getAttribute("fbAuthMode"));
        System.out.println("Session ID: " + session.getId());

        String authUrl = "https://www.facebook.com/v18.0/dialog/oauth" +
                "?client_id=" + FACEBOOK_APP_ID +
                "&redirect_uri=" + redirectUri +
                "&state=" + state +
                "&scope=email,public_profile";

        // Thêm response header để tránh cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        response.sendRedirect(authUrl);
    }
}