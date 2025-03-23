package com.example.backend.controller.auth;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;

import com.google.api.client.json.gson.GsonFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;


@WebServlet("/google-callback")
public class GoogleCallbackServlet extends HttpServlet {
    private static final String CLIENT_ID =  System.getenv("GOOGLE_CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");
    private static final String REDIRECT_URI = "http://localhost:8080/backend_war/google-callback";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            response.sendRedirect("login.jsp?error=Google login failed");
            return;
        }

        try {
            // Exchange code for access token
            TokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    JSON_FACTORY,
                    "https://oauth2.googleapis.com/token",
                    CLIENT_ID,
                    CLIENT_SECRET,
                    code,
                    REDIRECT_URI
            ).execute();

            String idTokenString = tokenResponse.get("id_token").toString();

            // Verify ID Token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JSON_FACTORY)
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String userId = payload.getSubject();
                String email = payload.getEmail();
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");

                // Lưu session
                request.getSession().setAttribute("user", email);
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("login.jsp?error=Invalid ID token");
            }

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Google login failed");
        }
    }
}