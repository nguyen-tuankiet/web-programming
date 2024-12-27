package com.example.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.backend.model.DAO.ImageDao;


import java.util.Map;

public class ImageService {
    private final Cloudinary cloudinary;
    private final ImageDao imageDao;

    public ImageService(Cloudinary cloudinary, ImageDao imageDao) {
        this.cloudinary = cloudinary;
        this.imageDao = imageDao;
    }

    // Upload ảnh lên Cloudinary và lưu vào DB
    public String uploadImage(byte[] fileBytes) throws Exception {
        Map uploadResult = cloudinary.uploader().upload(fileBytes, ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        imageDao.saveImage(imageUrl); // Lưu URL vào cơ sở dữ liệu
        return imageUrl;
    }

}