package com.example.backend.controller.auth;

import com.example.backend.config.EnvConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/register-facebook")
public class FacebookRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String FACEBOOK_APP_ID = EnvConfig.get("FACEBOOK_APP_ID");
    private final String REDIRECT_URI = "http://modernhome.property/facebook-callback";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String state = UUID.randomUUID().toString();

        String authUrl = "https://www.facebook.com/v18.0/dialog/oauth" +
                "?client_id=" + FACEBOOK_APP_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&state=" + state +
                "&scope=email,public_profile";

        request.getSession().setAttribute("fbAuthMode", "register");

        response.sendRedirect(authUrl);
    }
}
