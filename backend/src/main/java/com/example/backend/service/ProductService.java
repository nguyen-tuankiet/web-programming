package com.example.backend.service;

import java.util.List;

import com.example.backend.model.DAO.ProductDAO;
import com.example.backend.model.Product;
import org.jdbi.v3.core.Jdbi;


public class ProductService {
    Jdbi jdbi;
    public ProductService(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    public List<Product> getProductsByCategory(int categoryId){
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getProductsByCategory(categoryId));
    }
}
