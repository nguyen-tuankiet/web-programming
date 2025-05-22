package com.example.backend.model.DAO;

import com.example.backend.model.Category;
import com.example.backend.model.CategoryWithStock;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Category.class)
public interface CategoryDAO {



    @SqlQuery("SELECT * FROM categories WHERE isActive = 1")
    List<Category> getAllCategories();

    @SqlQuery("SELECT * FROM categories WHERE id = :id")
    Category getCategoryById(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO categories (name, isActive) VALUES (:name, :isActive)")
    @GetGeneratedKeys("id")
    int createCategory(@Bind("name") String name, @Bind("isActive") boolean isActive);


    @SqlUpdate("UPDATE categories SET name = :name WHERE id = :id")
    void updateCategory(@Bind("id") Integer id, @Bind("name") String name);

    @SqlUpdate("DELETE FROM categories WHERE id = :id")
    void deleteCategory(@Bind("id") Integer id);
//
//    @SqlUpdate("UPDATE categories SET isActive = :isActive WHERE id = :id")
//    void updateCategoryStatus(@Bind("id") Integer id, @Bind("isActive") Boolean isActive);

    @SqlUpdate("UPDATE categories SET isActive = :isActive WHERE id = :id")
    void updateCategoryStatus(@Bind("id") Integer id, @Bind("isActive") int isActive);


//    @SqlQuery("""
//    SELECT c.id, c.name, c.isActive, COUNT(p.id) AS totalStock
//    FROM categories c
//    LEFT JOIN products p ON p.categoryId = c.id
//    GROUP BY c.id, c.name, c.isActive
//""")
@SqlQuery("""
    SELECT c.id,
           c.name,
           CASE WHEN c.isActive = 1 THEN TRUE ELSE FALSE END AS isActive,
           COUNT(p.id) AS totalStock
    FROM categories c
    LEFT JOIN products p ON p.categoryId = c.id
    WHERE c.isActive = 1
    GROUP BY c.id, c.name, c.isActive
""")
@RegisterConstructorMapper(CategoryWithStock.class)
List<CategoryWithStock> getCategoriesWithStock();


}
