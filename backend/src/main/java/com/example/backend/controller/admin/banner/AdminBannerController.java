package com.example.backend.controller.admin.banner;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Banner;
import com.example.backend.model.DAO.ImageDao;
import com.example.backend.service.BannerService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminBannerController", urlPatterns = {"/admin/banner"})
public class AdminBannerController extends HttpServlet {
    private final BannerService bannerService = new BannerService(DBConnection.getJdbi());
    private final ImageDao imageDao = DBConnection.getJdbi().onDemand(ImageDao.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy danh sách banner
        List<Banner> banners = bannerService.getAllBanners();

        Map<String, String> imageMap = new HashMap<>();
        for (Banner b : banners) {
            try {
                String url = imageDao.getImageUrlById(Integer.parseInt(b.getImageId()));
                imageMap.put(b.getImageId(), url);
            } catch (NumberFormatException e) {
                imageMap.put(b.getImageId(), ""); // fallback nếu imageId lỗi
            }
        }

        // Gửi dữ liệu sang JSP
        request.setAttribute("banners", banners);
        request.setAttribute("imageMap", imageMap);

        // Forward tới trang JSP
        request.getRequestDispatcher("/admin/banner.jsp").forward(request, response);
    }
}
