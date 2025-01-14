package com.example.backend.service;

import com.example.backend.model.Category;
import com.example.backend.model.DAO.CategoryRepository;
import org.jdbi.v3.core.Jdbi;

public class CategoryManager {
    private final CategoryRepository categoryRepository;

    public CategoryManager(Jdbi jdbi) {
        this.categoryRepository = jdbi.onDemand(CategoryRepository.class);
    }

    public void addCategory(Category category) {
        categoryRepository.addCategory(category.getName());
    }
}
