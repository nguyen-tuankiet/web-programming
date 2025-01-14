package com.example.backend.model.DAO;

import com.example.backend.model.Category;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;

@RegisterConstructorMapper(Category.class)
public interface CategoryDAO {
}
