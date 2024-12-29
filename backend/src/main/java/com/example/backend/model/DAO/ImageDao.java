package com.example.backend.model.DAO;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.customizer.Bind;


public interface ImageDao {

    @SqlUpdate("INSERT INTO image (url) VALUES (:url)")
    @GetGeneratedKeys
    int saveImage(@Bind("url") String url);

    @SqlUpdate("INSERT INTO product_images (productId, imageId) VALUES (:productId, :imageId)")
    int addImageToProduct(@Bind("productId") Integer productId, @Bind("imageId") Integer imageId);

}