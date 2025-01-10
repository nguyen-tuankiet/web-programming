package com.example.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.backend.model.DAO.ImageDao;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Map;

public class ImageService {
    Jdbi jdbi;
    private  Cloudinary cloudinary;

    private  ImageDao imageDao;

    public ImageService(Jdbi jdbi, Cloudinary cloudinary, ImageDao imageDao) {
        this.jdbi = jdbi;
        this.cloudinary = cloudinary;
        this.imageDao = imageDao;
    }


    public ImageService(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.imageDao = jdbi.onDemand(ImageDao.class);
    }


    public ImageService(Cloudinary cloudinary, ImageDao imageDao) {
        this.cloudinary = cloudinary;
        this.imageDao = imageDao;
    }

    // Upload ảnh lên Cloudinary và lưu vào DB
    public String uploadImage(byte[] fileBytes) throws Exception {
        Map uploadResult = cloudinary.uploader().upload(fileBytes, ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        imageDao.saveImage(imageUrl);
        return imageUrl;
    }

    public boolean addImageToProduct(Integer productId, Integer imageId) {
        return imageDao.addImageToProduct(productId, imageId);
    }

    public int saveImage(String imageUrl) {
        return imageDao.saveImage(imageUrl);
    }

    public List<String> getAllImagesByProductId(Integer productId) {
        return imageDao.getAllImagesByProductId(productId);
    }
//    public static void main(String[] args) {
//        // Tạo kết nối với cơ sở dữ liệu
//        ProductService productService = new ProductService(DBConnection.getJdbi());
//        ImageService imageService = new ImageService(DBConnection.getJdbi());
//
//        try {
//            // ID sản phẩm để kiểm tra
//            int productId = 1; // Thay bằng một ID thực trong cơ sở dữ liệu của bạn
//
//            // Lấy danh sách ảnh của sản phẩm từ ImageService
//            List<String> images = imageService.getAllImagesByProductId(productId);
//
//            // Kiểm tra và in danh sách ảnh
//            if (images != null && !images.isEmpty()) {
//                System.out.println("Images for Product ID " + productId + ":");
//                for (String image : images) {
//                    System.out.println(image);
//                }
//            } else {
//                System.out.println("No images found for Product ID " + productId);
//            }
//        } catch (Exception e) {
//            System.err.println("Error occurred: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

}