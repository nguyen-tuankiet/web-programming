package com.example.backend.model.DAO;

import com.example.backend.model.OptionVariant;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import com.example.backend.model.Product;

import java.util.List;


@RegisterConstructorMapper(Product.class)
public interface ProductDAO {

    @SqlQuery(value = " " +
            "SELECT p.id as id, p.name as name, p.description as description, " +
            "            p.sku as sku, p.isActive as isActive, p.brandId as brandId,  " +
            "            p.noOfViews as noOfViews, p.noOfSold as noOfSold,  " +
            "            p.categoryId as categoryId, p.primaryImage as primaryImage, " +
            "            ops.id as optionId ,ops.price as price, " +
            "            ops.stock as stock,  " +
            "            img.url as imageUrl  " +
            "            FROM products as p " +
            "                INNER JOIN categories as cate on cate.id = p.categoryId " +
            "                INNER JOIN `options` as ops on ops.productId = p.id " +
            "                inner join image as img on p.primaryImage = img.id " +
            "            WHERE cate.id= :categoryId and ops.price = ( " +
            "                    SELECT MIN(price) " +
            "                    FROM options as ops " +
            "                    WHERE p.id = ops.productId and ops.stock > 0 " +
            "                       and p.isActive = true );")
    @RegisterConstructorMapper(Product.class)
    List<Product> getProductsByCategory(@Bind("categoryId") int categoryId);


//    @SqlQuery("SELECT p.id as id, p.name as name, p.description as description, " +
//            "p.sku as sku, p.isActive as isActive, p.brandId as brandId, " +
//            "p.noOfViews as noOfViews, p.noOfSold as noOfSold, " +
//            "p.categoryId as categoryId, p.primaryImage as primaryImage " +
//            "FROM products p WHERE p.id = :id")


    @SqlQuery(value = "SELECT p.id           as id,\n" +
            "       p.name         as name,\n" +
            "       p.description  as description,\n" +
            "       p.sku          as sku,\n" +
            "       p.isActive     as isActive,\n" +
            "       p.brandId      as brandId,\n" +
            "       p.noOfViews    as noOfViews,\n" +
            "       p.noOfSold     as noOfSold,\n" +
            "       p.categoryId   as categoryId,\n" +
            "       p.primaryImage as primaryImage,\n" +
            "       ops.id         as optionId,\n" +
            "       ops.price      as price,\n" +
            "       ops.stock      as stock,\n" +
            "       img.url        as imageUrl, \n" +
            " p.height as height , p.length as length , p.width as width , p.weight as weight " +

            "FROM products as p\n" +
            "         INNER JOIN categories as cate on cate.id = p.categoryId\n" +
            "         INNER JOIN `options` as ops on ops.productId = p.id\n" +
            "         inner join image as img on p.primaryImage = img.id\n" +
            "WHERE p.id = :id" +
            "  and ops.price = (SELECT MIN(price)\n" +
            "                   FROM options as ops\n" +
            "                   WHERE p.id = ops.productId\n" +
            "                     and ops.stock > 0)")
    @RegisterConstructorMapper(Product.class)
    Product getProductById(@Bind("id") int id);


    @SqlQuery(value =
            "SELECT p.id as id, p.name as name, p.description as description, " +
                    " p.height as height , p.length as length , p.width as width , p.weight as weight, " +
                    "         p.isActive as isActive, " +
                    "       p.noOfViews as noOfViews, p.noOfSold as noOfSold, " +
                    "        p.primaryImage as primaryImage, " +
                    "       ops.id as optionId ,ops.price as price, " +
                    "       ops.stock as stock, " +
                    "       img.url as imageUrl " +
                    "FROM products as p " +
                    "         INNER JOIN categories as cate on cate.id = p.categoryId " +
                    "         INNER JOIN `options` as ops on ops.productId = p.id " +
                    "         inner join image as img on p.primaryImage = img.id " +
                    "WHERE p.id= :productId and ops.id =:optionId ;")

    @RegisterConstructorMapper(Product.class)
    Product getProductByIdAndOptionId(@Bind("productId") int productId,
                                      @Bind("optionId") int optionId);

    @SqlQuery(value = "SELECT p.id, p.name, p.sku, p.description, p.isActive, " +
            "       p.categoryId, cate.name as categoryName, " +
            "       p.brandId, p.noOfViews, p.noOfSold, " +
            "       p.primaryImage, img.url as imageUrl, " +
            "       ops.price, ops.stock, ops.id as optionId " +
            "FROM products p " +
            "         INNER JOIN categories cate ON cate.id = p.categoryId " +
            "         INNER JOIN options ops ON ops.productId = p.id " +
            "         INNER JOIN image img ON img.id = p.primaryImage " +
            "WHERE p.isActive = true and stock > 0"
    )
    @RegisterConstructorMapper(Product.class)
    List<Product> getAllProducts();

    @SqlQuery("SELECT price FROM options WHERE productId = :productId AND stock > 0 ORDER BY price ASC LIMIT 1")
    Integer getMinimumPriceForProduct(@Bind("productId") int productId);

    @SqlQuery("SELECT price FROM options WHERE id = :optionId AND stock > 0")
    Integer getPriceForOption(@Bind("optionId") int optionId);


    @SqlUpdate("INSERT INTO products (name,description, isActive, categoryId, brandId, noOfViews, noOfSold, primaryImage, sku, height,length, width, weight ) "
            + "VALUES (:name, :description,COALESCE(:isActive, 1), :categoryId, :brandId, 0, 0, COALESCE(:primaryImage, NULL), :sku,:height, :length, :width, :weight )")
    @GetGeneratedKeys
    int addProduct(@Bind("name") String name,
                   @Bind("description") String description,
                   @Bind("isActive") Boolean isActive,
                   @Bind("categoryId") Integer categoryId,
                   @Bind("brandId") Integer brandId,
                   @Bind("primaryImage") Integer primaryImage,
                   @Bind("sku") String sku,
                   @Bind("height") Integer height,
                   @Bind("length") Integer length,
                   @Bind("width") Integer width,
                   @Bind("weight") Integer weight
    );

    @SqlQuery("""
               SELECT p.id AS id, p.name AS name, p.primaryImage AS image, i.url AS imageUrl, o.price AS price
               FROM products p
               LEFT JOIN options o ON p.id = o.productId
               LEFT JOIN image i ON p.primaryImage = i.id
               WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:name), '%')
            """)
    @RegisterConstructorMapper(Product.class)
    List<Product> searchProducts(@Bind("name") String name);


//
//
//    @SqlQuery("""
//   SELECT p.id AS id, p.name AS name, p.primaryImage AS image, i.url AS imageUrl, o.price AS price
//   FROM products p
//   LEFT JOIN options o ON p.id = o.productId
//   LEFT JOIN image i ON p.primaryImage = i.id
//   WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:name), '%')
//   LIMIT : limit
//""")
//    @RegisterConstructorMapper(Product.class)
//    List<Product> searchProducts(@Bind("name") String name, @Bind("limit") int limit);
//


    @SqlQuery("""
                SELECT p.id AS id, p.name AS name, p.primaryImage AS image, i.url AS imageUrl, o.price AS price
                FROM products p
                LEFT JOIN options o ON p.id = o.productId
                LEFT JOIN image i ON p.primaryImage = i.id
                WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:name), '%')
                LIMIT :limit OFFSET :offset
            """)
    List<Product> searchProducts(@Bind("name") String name, @Bind("limit") int limit, @Bind("offset") int offset);


    @SqlQuery(value = "SELECT p.id           as id, " +
            "       p.name         as name, " +
            "       p.description  as description, " +
            "       p.sku          as sku, " +
            "       p.isActive     as isActive, " +
            "       p.brandId      as brandId, " +
            "       p.noOfViews    as noOfViews, " +
            "       p.noOfSold     as noOfSold, " +
            "       p.categoryId   as categoryId, " +
            "       p.primaryImage as primaryImage, " +
            "       ops.id         as optionId, " +
            "       ops.price      as price, " +
            "       ops.stock      as stock, " +
            "       img.url        as imageUrl " +
            "FROM products as p " +
            "         INNER JOIN categories as cate on cate.id = p.categoryId " +
            "         INNER JOIN `options` as ops on ops.productId = p.id " +
            "         inner join image as img on p.primaryImage = img.id " +
            "WHERE cate.id = :categoryId " +
            "  and ops.price = (SELECT MIN(price) " +
            "                   FROM options as ops " +
            "                   WHERE p.id = ops.productId " +
            "                     and ops.stock > 0" +
            "                       and p.isActive = true ) " +
            "order by p.noOfViews desc , p.noOfSold desc " +
            "limit 3")
    public List<Product> getTopProductsByCategoryId(@Bind("categoryId") int categoryId, @Bind("limit") Integer limit);


    @SqlUpdate("UPDATE products SET isActive = false WHERE id = :id")
    boolean deactivateProduct(@Bind("id") int id);

    @SqlQuery(value = """
                SELECT p.id as id, p.name as name, p.description as description,
                       p.sku as sku, p.isActive as isActive, p.brandId as brandId,  
                       p.noOfViews as noOfViews, p.noOfSold as noOfSold,  
                       p.categoryId as categoryId, p.primaryImage as primaryImage,
                       ops.id as optionId, ops.price as price,
                       ops.stock as stock,  
                       img.url as imageUrl,
                       v.id as variantId,
                       vv.id as variantValueId,
                       vv.value as variantValueName,
                       v.name as variantName,
                       p.height as height,
                       p.length as length,
                       p.width as width,
                       p.weight as weight
                FROM products as p 
                    INNER JOIN categories as cate on cate.id = p.categoryId 
                    INNER JOIN `options` as ops on ops.productId = p.id 
                    INNER JOIN image as img on p.primaryImage = img.id 
                    INNER JOIN option_variant_value as ovv on ops.id = ovv.optionId 
                    INNER JOIN variant_value as vv on ovv.variantValueId = vv.id 
                    INNER JOIN variant as v on vv.variantId = v.id 
                WHERE p.id = :id 
                  AND ops.price = (
                        SELECT MIN(price) 
                        FROM options as ops 
                        WHERE p.id = ops.productId AND ops.stock > 0
                  );
            """)
    @RegisterConstructorMapper(Product.class)
    Product editProduct(@Bind("id") int id);


    @SqlQuery("""
                SELECT p.id as id, p.name as name, p.description as description,
                       p.sku as sku, p.isActive as isActive, p.brandId as brandId,  
                       p.noOfViews as noOfViews, p.noOfSold as noOfSold,  
                       p.categoryId as categoryId, p.primaryImage as primaryImage,
                       ops.id as optionId, ops.price as price,
                       ops.stock as stock,  
                       img.url as imageUrl,
                       v.id as variantId,
                       vv.id as variantValueId,
                       vv.value as variantValueName,
                       v.name as variantName 
                FROM products as p 
                    INNER JOIN categories as cate on cate.id = p.categoryId 
                    INNER JOIN `options` as ops on ops.productId = p.id 
                    INNER JOIN image as img on p.primaryImage = img.id 
                    INNER JOIN option_variant_value as ovv on ops.id = ovv.optionId 
                    INNER JOIN variant_value as vv on ovv.variantValueId = vv.id 
                    INNER JOIN variant as v on vv.variantId = v.id 
                WHERE p.id = :id 
                  AND ops.price = (
                        SELECT MIN(price) 
                        FROM options as ops 
                        WHERE p.id = ops.productId AND ops.stock > 0
                );
            """)
    @RegisterConstructorMapper(OptionVariant.class)
    List<OptionVariant> getVariants(@Bind("id") int id);


    @SqlUpdate(value = "update products\n" +
            "set noOfViews = noOfViews +1\n" +
            "where id = :id;")
    Boolean increaseNoOfViews(@Bind("id") int id);


    @SqlUpdate(value = "update products\n" +
            "set noOfSold = noOfSold + :quantity\n" +
            "where id = :id ;\n")
    Boolean increaseNoOfSold(@Bind("id") int id, @Bind("quantity") Integer quantity);


    @SqlQuery(value = "SELECT p.id           as id,\n" +
            "       p.name         as name,\n" +
            "       p.noOfViews    as noOfViews,\n" +
            "       p.noOfSold     as noOfSold,\n" +
            "       p.sku          as sku,\n" +
            "       p.isActive     as isActive,\n" +
            "       p.brandId      as brandId,\n" +
            "       p.categoryId   as categoryId,\n" +
            "       p.primaryImage as primaryImage,\n" +
            "       img.url        as imageUrl,\n" +
            "       ops.id         as optionId,\n" +
            "       ops.price      as price,\n" +
            "       ops.stock      as stock\n" +
            "FROM products as p\n" +
            "         INNER JOIN `options` as ops on ops.productId = p.id\n" +
            "         inner join image as img on p.primaryImage = img.id\n" +
            "where p.isActive = true\n" +
            "  and ops.price = (SELECT MIN(price)\n" +
            "                   FROM options as ops\n" +
            "                   WHERE p.id = ops.productId\n" +
            "                     and ops.stock > 0)\n" +
            "order by p.noOfSold desc, p.noOfViews desc\n" +
            "limit 12")
    List<Product> getTopProducts();


    @SqlQuery(
            """
                    SELECT\s
                            p.id           AS id,
                            p.name         AS name,
                            p.description  AS description,
                            p.sku          AS sku,
                            p.isActive     AS isActive,
                            p.brandId      AS brandId,
                            p.noOfViews    AS noOfViews,
                            p.noOfSold     AS noOfSold,
                            p.categoryId   AS categoryId,
                            p.primaryImage AS primaryImage,
                            opt.id         AS optionId,
                            opt.price      AS price,
                            opt.stock      AS stock,
                            img.url        AS imageUrl
                       \s
                        FROM products AS p
                            INNER JOIN options AS opt ON p.id = opt.productId
                            INNER JOIN image AS img ON img.id = p.primaryImage
                            INNER JOIN categories AS cate ON p.categoryId = cate.id
                            INNER JOIN option_variant_value AS ovv ON opt.id = ovv.optionId
                       \s
                        WHERE\s
                            ovv.variantValueId IN (<optionsId>)
                            AND cate.id = :categoryId
                            AND opt.price >= COALESCE(:minPrice, 0)
                            AND opt.price <= COALESCE(:maxPrice, 999999999)
                       \s""")

    public List<Product> filterProduct(
            @Bind("categoryId") int categoryId,
            @BindList(value = "optionsId") List<Integer> optionsId,
            @Bind("minPrice") @Nullable Integer minPrice,
            @Bind("maxPrice") @Nullable Integer maxPrice);


    @SqlQuery("""
            SELECT
                    p.id           AS id,
                    p.name         AS name,
                    p.description  AS description,
                    p.sku          AS sku,
                    p.isActive     AS isActive,
                    p.brandId      AS brandId,
                    p.noOfViews    AS noOfViews,
                    p.noOfSold     AS noOfSold,
                    p.categoryId   AS categoryId,
                    p.primaryImage AS primaryImage,
                    opt.id         AS optionId,
                    opt.price      AS price,
                    opt.stock      AS stock,
                    img.url        AS imageUrl
                    FROM products AS p
                    INNER JOIN options AS opt ON p.id = opt.productId
                    INNER JOIN image AS img ON img.id = p.primaryImage
                    INNER JOIN categories AS cate ON p.categoryId = cate.id
                    
                    WHERE 
                    cate.id = :categoryId
                    AND opt.price >= COALESCE(:minPrice, 0)
                    AND opt.price <= COALESCE(:maxPrice, 999999999)
    """)

    public List<Product> filterProductByPrice(
            @Bind("categoryId") int categoryId,
            @Bind("minPrice") @Nullable Integer minPrice,
            @Bind("maxPrice") @Nullable Integer maxPrice);


    @SqlQuery("SELECT p.id           as id, \n" +
            "                                          p.name         as name, \n" +
            "                                           p.description  as description, \n" +
            "                                           p.sku          as sku,  \n" +
            "                                           p.isActive     as isActive, \n" +
            "                                           p.brandId      as brandId, \n" +
            "                                           p.noOfViews    as noOfViews, \n" +
            "                                           p.noOfSold     as noOfSold,  \n" +
            "                                          p.categoryId   as categoryId,  \n" +
            "                                           p.primaryImage as primaryImage,  \n" +
            "                                           opt.id         as optionId, \n" +
            "                                           opt.price      as price, \n" +
            "                                           opt.stock      as stock,  \n" +
            "                                           img.url        as imageUrl\n" +
            "                        FROM products as p  \n" +
            "                         INNER JOIN options as opt ON p.id = opt.productId\n" +
            "                        INNER JOIN image as img on img.id = p.primaryImage\n" +
            "                        INNER JOIN categories as cate on p.categoryId = cate.id\n" +
            "                        INNER JOIN option_variant_value as ovv ON opt.id = ovv.optionId\n" +
            "                         ORDER BY p.noOfViews DESC, p.noOfSold DESC\n" +
            "                         LIMIT 3")

    public List<Product> suggestProduct();

    @SqlUpdate("""
            UPDATE products 
            SET name = :name,
                description = :description,
                sku = :sku,
                categoryId = :categoryId,
                brandId = :brandId,
                primaryImage = COALESCE(:primaryImage, primaryImage),
                height = :height,
                length = :length,
                width = :width,
                weight = :weight
            WHERE id = :id
            """)
    boolean updateProduct(@Bind("id") Integer id,
                          @Bind("name") String name,
                          @Bind("description") String description,
                          @Bind("sku") String sku,
                          @Bind("categoryId") Integer categoryId,
                          @Bind("brandId") Integer brandId,
                          @Bind("primaryImage") Integer primaryImage,
                          @Bind("height") Integer height,
                          @Bind("length") Integer length,
                          @Bind("width") Integer width,
                          @Bind("weight") Integer weight);

}