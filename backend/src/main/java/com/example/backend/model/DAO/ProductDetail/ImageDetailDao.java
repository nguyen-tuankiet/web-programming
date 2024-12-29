package com.example.backend.model.DAO.ProductDetail;

import com.example.backend.model.ImageDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDetailDao {

    private Connection connection;

    public ImageDetailDao(Connection connection) {
        this.connection = connection;
    }

    public List<ImageDetail> getAllImageDetails() throws SQLException {
        List<ImageDetail> images = new ArrayList<>();
        String query = "SELECT * FROM image";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ImageDetail image = new ImageDetail(rs.getInt("id"), rs.getString("url"));
                images.add(image);
            }
        }
        return images;
    }
}
