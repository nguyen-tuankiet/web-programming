package com.example.backend.model.DAO;

import com.example.backend.model.Category;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Category.class)
public interface CategoryDAO {

    @SqlQuery("SELECT * FROM category")
    List<Category> getAllCategories();

    @SqlQuery("SELECT * FROM category WHERE id = :id")
    Category getCategoryById(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO category (name) VALUES (:name)")
    void createCategory(@Bind("name") String name);

    @SqlUpdate("UPDATE category SET name = :name WHERE id = :id")
    void updateCategory(@Bind("id") Integer id, @Bind("name") String name);

    @SqlUpdate("DELETE FROM category WHERE id = :id")
    void deleteCategory(@Bind("id") Integer id);
}
