package com.example.backend.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.backend.model.Image;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.jdbi.v3.core.Jdbi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@WebServlet("/uploadImage")
@MultipartConfig
public class UploadImage extends HttpServlet {
    private Cloudinary cloudinary;
    private Jdbi jdbi;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Khởi tạo Cloudinary
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dshbh3gvj",
                    "api_key", "849919465828344",
                    "api_secret", "Al6Zb6SHDru3yMXbgEFyzNvUSKA"
            ));

            // Khởi tạo Jdbi
            jdbi = Jdbi.create("jdbc:mysql://localhost:3306/WEB_Ecommerce", "root", "");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL JDBC Driver không tìm thấy", e);
        } catch (Exception e) {
            throw new ServletException("Lỗi khi khởi tạo", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");
        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream()) {
                byte[] fileBytes = inputStream.readAllBytes();
                Map uploadResult = cloudinary.uploader().upload(fileBytes, ObjectUtils.emptyMap());
                String imageUrl = (String) uploadResult.get("url");

                // Lưu URL vào cơ sở dữ liệu
                Image image = new Image(null, imageUrl);
                jdbi.useHandle(handle -> {
                    handle.createUpdate("INSERT INTO image (url) VALUES (:url)")
                            .bind("url", image.getUrl())
                            .execute();
                });

                response.getWriter().println("Ảnh đã được upload thành công! URL: " + imageUrl);
            } catch (Exception e) {
                response.getWriter().println("Lỗi khi upload ảnh: " + e.getMessage());
            }

        } else {
            response.getWriter().println("Không tìm thấy file để upload.");
        }
    }
}
