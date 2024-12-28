package com.example.backend.model.DAO;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import com.example.backend.model.Product;
import java.util.List;

public interface ProductDAO {
    // Create product
    @SqlUpdate("INSERT INTO products (name, sku, description, isActive, categoryId, brandId, noOfViews, noOfSold, primaryImage, price) " +
            "VALUES (:name, :sku, :description, :isActive, :categoryId, :brandId, :noOfViews, :noOfSold, :primaryImage, :price)")
    @GetGeneratedKeys
    long addProduct(@Bind("name") String name,
                     @Bind("sku") String sku,
                     @Bind("description") String description,
                     @Bind("isActive") boolean isActive,
                     @Bind("categoryId") int categoryId,
                     @Bind("brandId") int brandId,
                     @Bind("noOfViews") int noOfViews,
                     @Bind("noOfSold") int noOfSold,
                     @Bind("primaryImage") Integer primaryImage,
                     @Bind("price") int price);


    // Liên kết sản phẩm với hình ảnh trong bảng liên kết product_images
    @SqlUpdate("INSERT INTO product_images (productId, imageId) VALUES (:productId, :imageId)")
    void linkProductToImage(@Bind("productId") long productId, @Bind("imageId") long imageId);

    // Cập nhật hình ảnh chính của sản phẩm
    @SqlUpdate("UPDATE products SET primaryImage = :primaryImage WHERE id = :productId")
    void updatePrimaryImage(@Bind("productId") long productId, @Bind("primaryImage") long primaryImage);



    //get product by category
    @SqlQuery("SELECT p.id as id, p.name as name, p.description as description, " +
            "p.sku as sku, p.isActive as isActive, p.brandId as brandId, " +
            "p.noOfViews as noOfViews, p.noOfSold as noOfSold, " +
            "p.categoryId as categoryId, p.primaryImage as primaryImage, ops.price as price " +
            "FROM products as p INNER JOIN categories as cate on cate.id = p.categoryId " +
            "INNER JOIN `options` as ops on ops.productId = p.id " +
            "WHERE cate.id= :categoryId and ops.price = (SELECT MIN(price) " +
            " FROM options " +
            " WHERE p.id = options.productId) ")
    @RegisterConstructorMapper(Product.class)
    List<Product> getProductsByCategory(@Bind("categoryId") int categoryId);
}
