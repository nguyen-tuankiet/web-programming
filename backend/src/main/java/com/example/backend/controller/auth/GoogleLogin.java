package com.example.backend.controller.auth;

import com.example.backend.config.EnvConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/login-google")
public class GoogleLogin extends HttpServlet {
    String clientId = EnvConfig.get("GOOGLE_CLIENT_ID");
    String clientSecret = EnvConfig.get("GOOGLE_CLIENT_SECRET");
    private static final String REDIRECT_URI = "http://modernhome.property/google-callback";
    private static final String SCOPE = "email profile";
    private static final String AUTH_ENDPOINT = "https://accounts.google.com/o/oauth2/v2/auth";

    @Override
    public void init() throws ServletException {
        try {

            if (clientId == null || clientId.trim().isEmpty()) {
                throw new ServletException("Google Client ID is not configured properly");
            }
            System.out.println("GoogleLogin initialized with Client ID: " + clientId);
        } catch (Exception e) {
            throw new ServletException("Error initializing Google Login: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Verify CLIENT_ID is available
            if (clientId == null || clientId.trim().isEmpty()) {
                throw new ServletException("Google Client ID is not configured");
            }

            // Generate a random state parameter to prevent CSRF
            String state = UUID.randomUUID().toString();
            request.getSession().setAttribute("google_state", state);
            request.getSession().setAttribute("google_action", "login");

            // Build the Google OAuth URL
            String authUrl = AUTH_ENDPOINT +
                    "?client_id=" + java.net.URLEncoder.encode(clientId, "UTF-8") +
                    "&redirect_uri=" + java.net.URLEncoder.encode(REDIRECT_URI, "UTF-8") +
                    "&response_type=code" +
                    "&scope=" + java.net.URLEncoder.encode(SCOPE, "UTF-8") +
                    "&state=" + state +
                    "&access_type=offline" +
                    "&prompt=consent";

            System.out.println("Redirecting to Google OAuth URL: " + authUrl);
            response.sendRedirect(authUrl);
        } catch (Exception e) {
            System.err.println("Error in Google Login: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/auth/auth.jsp?error=" +
                java.net.URLEncoder.encode("Error initializing Google Login: " + e.getMessage(), "UTF-8"));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}