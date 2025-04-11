package com.example.backend.controller.admin;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Banner;
import com.example.backend.model.DAO.ImageDao;
import com.example.backend.service.BannerService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "BannerController", value = "/admin/api/banner/*")
public class BannerController extends HttpServlet {
    private final BannerService bannerService = new BannerService(DBConnection.getJdbi());
    private final ImageDao imageDao = DBConnection.getJdbi().onDemand(ImageDao.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Banner> banners = bannerService.getAllBanners();
            Map<String, String> imageMap = new HashMap<>();

            for (Banner b : banners) {
                String url = imageDao.getImageUrlById(Integer.parseInt(b.getImageId()));
                imageMap.put(b.getImageId(), url);
            }

            request.setAttribute("banners", banners);
            request.setAttribute("imageMap", imageMap);
            request.getRequestDispatcher("/admin/banner.jsp").forward(request, response);
        } else {
            // API lấy theo ID
            String[] parts = pathInfo.split("/");
            if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);
                Banner banner = bannerService.getBannerById(id);
                ResponseWrapper<Banner> wrapper = new ResponseWrapper<>(200, "success", "Fetched banner", banner);
                writeJson(response, wrapper);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String body = request.getReader().lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> json = objectMapper.readValue(body, new TypeReference<>() {});

            String status = (String) json.get("status");

            String description = (String) json.get("description");

            String imageId = String.valueOf(json.get("imageId"));

            LocalDate startDate = LocalDate.parse((String) json.get("startDate"));
            LocalDate endDate = LocalDate.parse((String) json.get("endDate"));
            boolean isActive = json.get("isActive") != null && (Boolean) json.get("isActive");

            Banner banner = bannerService.createBanner(status, imageId, startDate, endDate, isActive, description);

            ResponseWrapper<Object> wrapper = new ResponseWrapper<>(201, "success", "Banner created", banner);
            writeJson(response, wrapper);

        } catch (Exception e) {
            e.printStackTrace(); // in log server
            response.setStatus(500);
            writeJson(response, Map.of(
                    "status", "error",
                    "message", "Server error: " + e.getMessage()
            ));
        }
    }



    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] pathParts = request.getPathInfo().split("/");
        int id = Integer.parseInt(pathParts[1]);

        String json = request.getReader().lines().collect(Collectors.joining());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonData = objectMapper.readValue(json, new TypeReference<>() {});

        if (jsonData.containsKey("isActive")) {
            boolean isActive = (Boolean) jsonData.get("isActive");
            bannerService.toggleBannerStatus(id, isActive);
            response.getWriter().write("{\"status\": \"success\"}");
            return;
        }

        bannerService.updateBanner(
                id,
                (String) jsonData.get("status"),
                (String) jsonData.get("imageId"),
                LocalDate.parse((String) jsonData.get("startDate")),
                LocalDate.parse((String) jsonData.get("endDate")),
                (String) jsonData.get("description")
        );

        response.getWriter().write("{\"status\": \"success\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getPathInfo().split("/")[1]);
        bannerService.deleteBanner(id);
        response.getWriter().write("{\"status\": \"success\"}");
    }

private void writeJson(HttpServletResponse response, Object data) throws IOException {
    response.setContentType("application/json");

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    // Để Jackson không serialize LocalDate thành timestamp
    mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    response.getWriter().write(mapper.writeValueAsString(data));
}

}
