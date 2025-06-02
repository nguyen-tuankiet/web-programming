package com.example.backend.controller.auth;
import com.google.gson.Gson;
import com.example.backend.Connection.DBConnection;
import com.example.backend.config.EnvConfig;
import com.example.backend.model.DAO.UserDao;
import com.example.backend.model.User;
import com.example.backend.service.AuthService;
import com.example.backend.service.EmailService;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jdbi.v3.core.Jdbi;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Properties;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@WebServlet("/google-callback")
public class GoogleCallbackServlet extends HttpServlet {
    String clientId = EnvConfig.get("GOOGLE_CLIENT_ID");
    String clientSecret = EnvConfig.get("GOOGLE_CLIENT_SECRET");
    private static final String REDIRECT_URI = "http://modernhome.property/google-callback";
    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
    private AuthService authService;
    private EmailService emailService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        try {
            Properties prop = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
            if (input == null) {
                throw new ServletException("Unable to find application.properties");
            }
            prop.load(input);
            if (clientId == null || clientSecret == null) {
                throw new ServletException("Google OAuth credentials not found in application.properties");
            }
            authService = new AuthService(DBConnection.getJdbi());
            emailService = new EmailService();

            // Initialize Gson with custom serializer for LocalDate
            gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> {
                    if (src == null) {
                        return null;
                    }
                    return context.serialize(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
                })
                .create();
        } catch (IOException e) {
            throw new ServletException("Error loading Google OAuth credentials", e);
        }
    }

    private String makePostRequest(String url, String postData) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = postData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    errorResponse.append(line);
                }
                throw new IOException("HTTP error code: " + responseCode + ", Response: " + errorResponse.toString());
            }
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response.toString();
    }

    private String makeGetRequest(String url, String accessToken) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    errorResponse.append(line);
                }
                throw new IOException("HTTP error code: " + responseCode + ", Response: " + errorResponse.toString());
            }
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            String sessionState = (String) request.getSession().getAttribute("google_state");
            String googleAction = (String) request.getSession().getAttribute("google_action");

            // Verify state parameter
            if (state == null || !state.equals(sessionState)) {
                response.sendRedirect(request.getContextPath() + "/auth/auth.jsp?error=Invalid state parameter");
                return;
            }

            // Get access token
            String postData = String.format(
                    "client_id=%s&client_secret=%s&redirect_uri=%s&code=%s&grant_type=authorization_code",
                    URLEncoder.encode(clientId, "UTF-8"),
                    URLEncoder.encode(clientSecret, "UTF-8"),
                    URLEncoder.encode(REDIRECT_URI, "UTF-8"),
                    URLEncoder.encode(code, "UTF-8")
            );

            String responseToken = makePostRequest(TOKEN_URL, postData);
            JsonObject jobj = gson.fromJson(responseToken, JsonObject.class);
            String accessToken = jobj.get("access_token").getAsString();

            // Get user info using the new endpoint
            String userInfoResponse = makeGetRequest(USER_INFO_URL, accessToken);
            JsonObject userInfo = gson.fromJson(userInfoResponse, JsonObject.class);
            String email = userInfo.get("email").getAsString();
            String name = userInfo.get("name").getAsString();

            // Check if user exists
            User user = authService.getUserByEmail(email);

            if (user == null) {
                if ("register".equals(googleAction)) {
                    // Generate a random password for the new user
                    String randomPassword = UUID.randomUUID().toString();

                    // Register new user with Google and the random password
                    boolean registrationSuccess = authService.registerWithGoogle(name, name, email, randomPassword);
                    if (registrationSuccess) {
                        // Send registration confirmation email with the same password
                        EmailService.sendRegistrationEmail(email, randomPassword);

                        // Redirect to login page with success message
                        response.sendRedirect(request.getContextPath() + "/login?message=Registration successful. Please check your email for login information.");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/login?error=Registration failed");
                    }
                } else {
                    // User doesn't exist and not registering
                    response.sendRedirect(request.getContextPath() + "/login?error=Account does not exist. Please register first.");
                }
            } else {
                if ("register".equals(googleAction)) {
                    // User already exists and trying to register
                    response.sendRedirect(request.getContextPath() + "/login?error=Account already exists. Please login.");
                } else {
                    // User exists, login
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    // Create a script to store basic user info in sessionStorage
                    String script = "<script>" +
                            "sessionStorage.setItem('role', '" + user.getRole() + "');" +
                            "sessionStorage.setItem('sessionId', '" + session.getId() + "');" +
                            "sessionStorage.setItem('userId', '" + user.getId() + "');" +
                            "if (sessionStorage.getItem('role') === 'ADMIN') {" +
                            "window.location.href = '" +"admin/dashboard';" +
                            "} else {" +
                            "window.location.href = '" + request.getContextPath() + "/home?success=Login successful';" +
                            "}" +
                            "</script>";

                    response.setContentType("text/html");
                    response.getWriter().write(script);
                }
            }

            // Clear session attributes
            request.getSession().removeAttribute("google_state");
            request.getSession().removeAttribute("google_action");

        } catch (Exception e) {
            System.err.println("Error in Google callback: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login?error=Google login failed");
        }
    }
}