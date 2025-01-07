package com.example.backend.model.DAO;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;


public interface ImageDao {

    @SqlUpdate("INSERT INTO image (url) VALUES (:url)")
    @GetGeneratedKeys
    int saveImage(@Bind("url") String url);

    @SqlUpdate("INSERT INTO product_images (productId, imageId) VALUES (:productId, :imageId)")
    boolean addImageToProduct(@Bind("productId") Integer productId, @Bind("imageId") Integer imageId);

    @SqlQuery(value = "SELECT image.url from image " +
            "INNER JOIN product_images ON image.id = product_images.imageId " +
            "WHERE product_images.productId = :productId ")
    List<String> getAllImagesByProductId(@Bind("productId") Integer productId);
}