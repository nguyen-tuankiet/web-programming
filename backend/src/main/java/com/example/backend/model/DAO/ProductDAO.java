package com.example.backend.model.DAO;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import com.example.backend.model.Product;
import java.util.List;

public interface ProductDAO {

    @SqlQuery(value = "\n" +
            "SELECT p.id as id, p.name as name, p.description as description,\n" +
            "            p.sku as sku, p.isActive as isActive, p.brandId as brandId, \n" +
            "            p.noOfViews as noOfViews, p.noOfSold as noOfSold, \n" +
            "            p.categoryId as categoryId, p.primaryImage as primaryImage,\n" +
            "            ops.id as optionId ,ops.price as price,\n" +
            "            ops.stock as stock, \n" +
            "            img.url as imageUrl \n" +
            "            FROM products as p\n" +
            "                INNER JOIN categories as cate on cate.id = p.categoryId\n" +
            "                INNER JOIN `options` as ops on ops.productId = p.id\n" +
            "                inner join image as img on p.primaryImage = img.id\n" +
            "            WHERE cate.id= :categoryId and ops.price = (\n" +
            "                    SELECT MIN(price)\n" +
            "                    FROM options as ops\n" +
            "                    WHERE p.id = ops.productId and ops.stock > 0);")
    @RegisterConstructorMapper(Product.class)
    List<Product> getProductsByCategory(@Bind("categoryId") int categoryId);


    @SqlQuery("SELECT p.id as id, p.name as name, p.description as description, " +
            "p.sku as sku, p.isActive as isActive, p.brandId as brandId, " +
            "p.noOfViews as noOfViews, p.noOfSold as noOfSold, " +
            "p.categoryId as categoryId, p.primaryImage as primaryImage " +
            "FROM products p WHERE p.id = :id")
    @RegisterConstructorMapper(Product.class)
    Product getProductById(@Bind("id") int id);



    @SqlQuery(value=
            "SELECT p.id as id, p.name as name, p.description as description,\n" +
                    "         p.isActive as isActive,\n" +
                    "       p.noOfViews as noOfViews, p.noOfSold as noOfSold,\n" +
                    "        p.primaryImage as primaryImage,\n" +
                    "       ops.id as optionId ,ops.price as price,\n" +
                    "       ops.stock as stock,\n" +
                    "       img.url as imageUrl\n" +
                    "FROM products as p\n" +
                    "         INNER JOIN categories as cate on cate.id = p.categoryId\n" +
                    "         INNER JOIN `options` as ops on ops.productId = p.id\n" +
                    "         inner join image as img on p.primaryImage = img.id\n" +
                    "WHERE p.id= :productId and ops.id =:optionId ;" )

    @RegisterConstructorMapper(Product.class)
    Product getProductByIdAndOptionId(@Bind("productId") int productId,
                                      @Bind("optionId") int optionId);

    @SqlQuery(value = "SELECT p.id, p.name, p.sku, p.description, p.isActive,\n" +
            "       p.categoryId, cate.name as categoryName,\n" +
            "       p.brandId, p.noOfViews, p.noOfSold,\n" +
            "       p.primaryImage, img.url as imageUrl,\n" +
            "       ops.price, ops.stock, ops.id as optionId\n" +
            "FROM products p\n" +
            "         INNER JOIN categories cate ON cate.id = p.categoryId\n" +
            "         INNER JOIN options ops ON ops.productId = p.id\n" +
            "         INNER JOIN image img ON img.id = p.primaryImage\n" +
            "WHERE ops.stock > 0 "
)
    @RegisterConstructorMapper(Product.class)
    List<Product> getAllProducts();

    @SqlQuery("SELECT price FROM options WHERE productId = :productId AND stock > 0 ORDER BY price ASC LIMIT 1")
    Integer getMinimumPriceForProduct(@Bind("productId") int productId);

    @SqlQuery("SELECT price FROM options WHERE id = :optionId AND stock > 0")
    Integer getPriceForOption(@Bind("optionId") int optionId);



    @SqlUpdate("INSERT INTO products (name,description, isActive, categoryId, brandId, noOfViews, noOfSold, primaryImage, sku) "
            + "VALUES (:name, :description,COALESCE(:isActive, 1), :categoryId, :brandId, 0, 0, COALESCE(:primaryImage, NULL), :sku)")
    int addProduct(@Bind("name") String name,
                   @Bind("description") String description,
                   @Bind("isActive") Boolean isActive,
                   @Bind("categoryId") Integer categoryId,
                   @Bind("brandId") Integer brandId,
                   @Bind("primaryImage") Integer primaryImage,
                   @Bind("sku") String sku);

}
