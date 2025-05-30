package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.User;
import com.example.backend.service.AuthService;
import com.example.backend.util.HashUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
    private final AuthService authService = new AuthService(DBConnection.getJdbi());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("resetEmail");

        if (email == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Phiên làm việc đã hết hạn. Vui lòng thực hiện lại từ đầu.");
            return;
        }

        if (newPassword == null || confirmPassword == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Vui lòng nhập đầy đủ thông tin mật khẩu.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Mật khẩu không khớp.");
            return;
        }

        try {
            User user = authService.getUserByEmail(email);
            if (user == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Không tìm thấy tài khoản.");
                return;
            }

            // Cập nhật mật khẩu mới
            authService.changePassword(user.getId(), null, newPassword, false);
            
            // Xóa email khỏi session sau khi đổi mật khẩu thành công
            session.removeAttribute("resetEmail");
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Đặt lại mật khẩu thành công!");
            
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Đã xảy ra lỗi khi đặt lại mật khẩu: " + e.getMessage());
        }
    }
}
