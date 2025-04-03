package com.example.backend.controller.auth;

import com.example.backend.config.EnvConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    String clientId = EnvConfig.get("GOOGLE_CLIENT_ID");
    String clientSecret = EnvConfig.get("GOOGLE_CLIENT_SECRET");


    @Override
    public void init() throws ServletException {
        try {
            Properties prop = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
            if (input == null) {
                throw new ServletException("Unable to find application.properties");
            }
            prop.load(input);
            clientId = EnvConfig.get("GOOGLE_CLIENT_ID");;
            if (clientId == null) {
                throw new ServletException("Google Client ID not found in application.properties");
            }
            System.out.println("Google Client ID loaded: " + clientId); // Debug log
        } catch (IOException e) {
            throw new ServletException("Error loading Google OAuth credentials", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("googleClientId", clientId);
        System.out.println("Setting Google Client ID in request: " + clientId); // Debug log
        request.getRequestDispatcher("/auth/auth.jsp").forward(request, response);
    }
} 