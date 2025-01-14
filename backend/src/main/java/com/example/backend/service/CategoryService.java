package com.example.backend.service;

import com.example.backend.model.Category;
import com.example.backend.model.DAO.CategoryDAO;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(Jdbi jdbi) {
        this.categoryDAO = jdbi.onDemand(CategoryDAO.class);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

//    public Category getCategoryById(Integer id) {
//        return categoryDAO.getCategoryById(id);
//    }

    public Category getCategoryById(Integer id) {
        Category category = categoryDAO.getCategoryById(id);
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }
        return category;
    }

    public Category createCategory(String name) {
        int id = categoryDAO.createCategory(name);
        return categoryDAO.getCategoryById(id);
    }

    public void updateCategory(Integer id, String name) {
        categoryDAO.updateCategory(id, name);
    }

    public void deleteCategory(Integer id) {
        categoryDAO.deleteCategory(id);
    }
}
