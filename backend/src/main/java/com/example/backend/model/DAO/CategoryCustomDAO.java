package com.example.backend.model.DAO;

import com.example.backend.model.CategoryWithStock;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterConstructorMapper(CategoryWithStock.class)
public interface CategoryCustomDAO {

    @SqlQuery("""
        SELECT c.id,
               c.name,
               c.isActive,
               COALESCE(SUM(o.stock), 0) AS totalStock
        FROM categories c
        LEFT JOIN products p ON c.id = p.categoryId
        LEFT JOIN options o ON p.id = o.productId
        GROUP BY c.id, c.name, c.isActive
    """)
    List<CategoryWithStock> getCustomCategoriesWithStock();


    @SqlQuery("SELECT c.id, c.name FROM categories c WHERE c.name LIKE CONCAT('%', :search, '%')")
    List<CategoryWithStock> searchCategoriesByName(@Bind("search") String search);
}
