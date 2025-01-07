package com.example.backend.controller.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.backend.model.DAO.ImageDao;
import com.example.backend.model.Image;
import com.example.backend.service.ImageService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
@WebServlet("/getUploadedImages")
public class UploadImageController extends HttpServlet {
    private ImageService imageService;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Load cấu hình từ application.properties
            Properties properties = new Properties();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
                if (input == null) {
                    throw new ServletException("Không tìm thấy file application.properties");
                }
                properties.load(input);
            }

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            // Khởi tạo Cloudinary
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dshbh3gvj",
                    "api_key", "849919465828344",
                    "api_secret", "Al6Zb6SHDru3yMXbgEFyzNvUSKA"
            ));

            // Khởi tạo Jdbi và đăng ký plugin SqlObject
            Jdbi jdbi = Jdbi.create(dbUrl, dbUsername, dbPassword);
            jdbi.installPlugin(new SqlObjectPlugin());

            // Tạo DAO từ Jdbi
            ImageDao imageDao = jdbi.onDemand(ImageDao.class);

            // Khởi tạo Service
            imageService = new ImageService(cloudinary, imageDao);

        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL JDBC Driver không tìm thấy", e);
        } catch (Exception e) {
            throw new ServletException("Lỗi khi khởi tạo", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Lấy danh sách các ảnh từ cơ sở dữ liệu
            List<Image> images = imageService.getAllImages();

            if (!images.isEmpty()) {
                // Trả về danh sách ảnh dưới dạng JSON
                ResponseWrapper<Object> successResponse = new ResponseWrapper<>(
                        HttpServletResponse.SC_OK,
                        "Thành công",
                        "Danh sách ảnh được trả về.",
                        images
                );
                response.setStatus(HttpServletResponse.SC_OK); // 200
                response.getWriter().println(new ObjectMapper().writeValueAsString(successResponse));
            } else {
                // Trả về thông báo không có ảnh
                ResponseWrapper<Object> noDataResponse = new ResponseWrapper<>(
                        HttpServletResponse.SC_OK,
                        "Thành công",
                        "Không có ảnh nào trong cơ sở dữ liệu.",
                        null
                );
                response.setStatus(HttpServletResponse.SC_OK); // 200
                response.getWriter().println(new ObjectMapper().writeValueAsString(noDataResponse));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Trả về lỗi
            ResponseWrapper<Object> errorResponse = new ResponseWrapper<>(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Lỗi",
                    "Có lỗi xảy ra khi lấy danh sách ảnh.",
                    null
            );
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            response.getWriter().println(new ObjectMapper().writeValueAsString(errorResponse));
        }
    }
}
