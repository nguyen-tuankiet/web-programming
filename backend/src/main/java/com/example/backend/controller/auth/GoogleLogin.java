package com.example.backend.controller.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/login-google")
public class GoogleLogin extends HttpServlet {
    private static final String CLIENT_ID =  System.getenv("GOOGLE_CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");
    private static final String REDIRECT_URI = "http://localhost:8080/backend_war/google-callback";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoogleAuthorizationCodeRequestUrl url = new GoogleAuthorizationCodeRequestUrl(
                CLIENT_ID,
                REDIRECT_URI,
                Arrays.asList("email", "profile"))
                .setAccessType("offline");

        String authUrl = url.build();
        response.sendRedirect(authUrl);
    }
}