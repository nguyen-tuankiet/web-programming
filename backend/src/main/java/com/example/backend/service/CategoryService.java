package com.example.backend.service;

import com.example.backend.model.Category;
import com.example.backend.model.DAO.CategoryDAO;
import org.jdbi.v3.core.Jdbi;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(Jdbi jdbi) {
        this.categoryDAO = jdbi.onDemand(CategoryDAO.class);
    }

    public void addCategory(Category category) {
        categoryDAO.addCategory(category.getName());
    }
}
