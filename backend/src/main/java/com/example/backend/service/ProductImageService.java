package com.example.backend.service;

import com.example.backend.model.DAO.ProductDetail.ProductImageDao;
import com.example.backend.model.ProductImage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductImageService {

    private ProductImageDao productImageDao;

    public ProductImageService(Connection connection) {
        this.productImageDao = new ProductImageDao(connection);
    }

    public List<ProductImage> getImagesByProductId(int productId) throws SQLException {
        return productImageDao.getImagesByProductId(productId);
    }
}
