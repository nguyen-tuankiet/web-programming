package com.example.backend.model.DAO;


import com.example.backend.model.Brand;
import com.example.backend.model.Category;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Brand.class)
public interface BrandDAO {
    @SqlQuery("SELECT * FROM brand")
    List<Brand> getAllBrand();

    @SqlQuery("SELECT * FROM brand WHERE id = :id")
    Brand getBrandById(@Bind("id") Integer id);

    @SqlUpdate("INSERT INTO brand (name) VALUES (:name)")
    @GetGeneratedKeys("id")
    int createBrand(@Bind("name") String name);


    @SqlUpdate("UPDATE brand SET name = :name WHERE id = :id")
    void updateBrand(@Bind("id") Integer id, @Bind("name") String name);

    @SqlUpdate("DELETE FROM brand WHERE id = :id")
    void deleteBrand(@Bind("id") Integer id);


    @SqlUpdate("INSERT INTO brand (name, isActive) VALUES (:name, :isActive)")
    @GetGeneratedKeys("id")
    int createBrand(@Bind("name") String name, @Bind("isActive") boolean isActive);


    @SqlUpdate("UPDATE brand SET isActive = :isActive WHERE id = :id")
    void updateBrandStatus(@Bind("id") Integer id, @Bind("isActive") boolean isActive);

}
