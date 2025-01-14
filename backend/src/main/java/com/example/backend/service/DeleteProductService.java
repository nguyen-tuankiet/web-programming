package com.example.backend.service;

import com.example.backend.model.DAO.ProductDAO;
import org.jdbi.v3.core.Jdbi;

public class DeleteProductService {
    private final ProductDAO productDAO;

    public DeleteProductService(Jdbi jdbi) {
        this.productDAO = jdbi.onDemand(ProductDAO.class);
    }

    public boolean  deactivateProduct(int productId) {
        return productDAO.deactivateProduct(productId);
    }
}
