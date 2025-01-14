package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.CategoryWithStock;
import com.example.backend.model.DAO.CategoryCustomDAO;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CategoryCustomService {
    private final CategoryCustomDAO categoryCustomDAO;

    public CategoryCustomService(Jdbi jdbi) {
        this.categoryCustomDAO = jdbi.onDemand(CategoryCustomDAO.class);
    }

    public List<CategoryWithStock> getCustomCategoriesWithTotalStock() {
        return categoryCustomDAO.getCustomCategoriesWithStock();
    }

    public List<CategoryWithStock> searchCategories(String search) {
        return categoryCustomDAO.searchCategoriesByName(search);
    }


    public static void main(String[] args) {
        CategoryCustomService categoryCustomService =  new CategoryCustomService(DBConnection.getJdbi());
        List<CategoryWithStock> categories = categoryCustomService.getCustomCategoriesWithTotalStock();
        System.out.println(categories);
    }
}
