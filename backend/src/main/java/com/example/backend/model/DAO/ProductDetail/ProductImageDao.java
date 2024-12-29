package com.example.backend.model.DAO.ProductDetail;

import com.example.backend.model.ProductImage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductImageDao {

    private Connection connection;

    public ProductImageDao(Connection connection) {
        this.connection = connection;
    }

    public List<ProductImage> getImagesByProductId(int productId) throws SQLException {
        List<ProductImage> productImages = new ArrayList<>();
        String query = "SELECT * FROM product_images WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductImage productImage = new ProductImage(
                        rs.getInt("id"),
                        rs.getInt("productId"),
                        rs.getInt("imageId")
                );
                productImages.add(productImage);
            }
        }
        return productImages;
    }
}
