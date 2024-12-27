package com.example.backend.model.DAO;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import com.example.backend.model.Product;
import java.util.List;

public interface ProductDAO {

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
