package com.example.backend.service;

import com.example.backend.model.Brand;
import com.example.backend.model.Category;
import com.example.backend.model.DAO.BrandDAO;
import com.example.backend.model.DAO.CategoryDAO;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class BrandService {
    private final BrandDAO brandDAO;

    public BrandService(Jdbi jdbi) {
        this.brandDAO = jdbi.onDemand(BrandDAO.class);
    }

    public List<Brand> getAllBrands() {
        return brandDAO.getAllBrand();
    }

    public Brand getBrandById(Integer id) {
        return brandDAO.getBrandById(id);
    }

    public Brand createBrand(String name) {
        int id = brandDAO.createBrand(name);
        return brandDAO.getBrandById(id);
    }

    public void updateBrand(Integer id, String name) {
        brandDAO.updateBrand(id, name);
    }

    public void deleteBrand(Integer id) {
        brandDAO.deleteBrand(id);
    }
}
