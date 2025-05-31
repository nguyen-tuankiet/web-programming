package com.example.backend.controller.auth;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.ERole;
import com.example.backend.model.User;
import com.example.backend.service.AuthService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter("/admin/*") // Áp dụng cho tất cả các URL bắt đầu bằng /admin
public class AdminAuthorizationFilter implements Filter {
    private final AuthService authService = new AuthService(DBConnection.getJdbi());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        ERole role = (ERole) session.getAttribute("role");

        // Kiểm tra xem userId có tồn tại trong session hay không
        if (userId == null) {
            redirectToLoginWithMessage(request, response, "Bạn chưa đăng nhập.");
            return;
        }

        // Lấy thông tin người dùng từ cơ sở dữ liệu dựa trên userId từ session
        User user = authService.getUserById(userId);

        if (user == null || role == ERole.USER) {
            session.invalidate();
            redirectToLoginWithMessage(request, response, "Bạn không có quyền truy cập vào trang này.");
            return;
        }

        chain.doFilter(req, res);
    }

    private void redirectToLoginWithMessage(HttpServletRequest request, HttpServletResponse response, String message)
            throws IOException {
        // Đưa ra thông báo và redirect về trang login
        request.getSession().setAttribute("errorMessage", message);
        response.sendRedirect(request.getContextPath() + "/login");
    }
    @Override
    public void destroy() {}
}
