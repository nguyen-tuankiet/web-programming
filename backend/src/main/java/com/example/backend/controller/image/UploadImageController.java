package com.example.backend.controller.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.example.backend.model.DAO.ImageDao;
import com.example.backend.model.Image;
import com.example.backend.service.ImageService;
import com.example.backend.util.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/uploadImage")
@MultipartConfig
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
            throw new ServletException("MySQL  Driver không tìm thấy", e);
        } catch (Exception e) {
            throw new ServletException("Lỗi khi khởi tạo", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        Collection<Part> fileParts = request.getParts(); // Lấy tất cả các file từ request
        List<Image> uploadedImages = new ArrayList<>(); // Danh sách lưu thông tin các ảnh đã upload

        if (fileParts != null && !fileParts.isEmpty()) {
            for (Part filePart : fileParts) {
                String fileName = filePart.getSubmittedFileName();
                String contentType = filePart.getContentType();

                // Kiểm tra MIME type và phần mở rộng
                if (contentType != null && fileName != null &&
                        (contentType.equals("image/png") || contentType.equals("image/jpeg")) &&
                        (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))) {

                    if (filePart.getSize() > 0) { // Kiểm tra kích thước file hợp lệ
                        try (InputStream inputStream = filePart.getInputStream()) {
                            byte[] fileBytes = inputStream.readAllBytes();
                            String imageUrl = imageService.uploadImage(fileBytes);
                            int generatedId = imageService.saveImage(imageUrl);

                            // Thêm thông tin ảnh đã upload vào danh sách
                            uploadedImages.add(new Image(generatedId, imageUrl));
                        } catch (Exception e) {
                            // Ghi log lỗi (nếu cần)
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("File không hợp lệ: " + fileName + " (Loại: " + contentType + ")");
                }
            }

            if (!uploadedImages.isEmpty()) {
                // Trả về phản hồi thành công
                ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(
                        HttpServletResponse.SC_OK,
                        "Thành công",
                        "Tất cả ảnh đã được upload thành công!",
                        uploadedImages
                );
                response.setStatus(HttpServletResponse.SC_OK); // 200
                response.getWriter().println(objectMapper.writeValueAsString(responseWrapper));
            } else {
                // Trả về phản hồi nếu không có ảnh nào hợp lệ được upload
                ResponseWrapper<Object> errorResponse = new ResponseWrapper<>(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Lỗi",
                        "Chỉ chấp nhận ảnh PNG hoặc JPG hợp lệ để upload.",
                        null
                );
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
                response.getWriter().println(objectMapper.writeValueAsString(errorResponse));
            }
        } else {
            // Trả về phản hồi khi không tìm thấy file
            ResponseWrapper<Object> errorResponse = new ResponseWrapper<>(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Lỗi",
                    "Không tìm thấy file để upload.",
                    null
            );
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            response.getWriter().println(objectMapper.writeValueAsString(errorResponse));
        }
    }



}