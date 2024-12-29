package com.example.backend.service;

import com.example.backend.model.DAO.ProductDetail.ImageDetailDao;
import com.example.backend.model.ImageDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ImageDetailService {

    private ImageDetailDao imageDetailDao;

    public ImageDetailService(Connection connection) {
        this.imageDetailDao = new ImageDetailDao(connection);
    }

    public List<ImageDetail> getAllImageDetails() throws SQLException {
        return imageDetailDao.getAllImageDetails();
    }
}
