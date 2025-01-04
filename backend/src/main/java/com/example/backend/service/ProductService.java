package com.example.backend.service;

import java.util.List;

import com.example.backend.model.DAO.ImageDao;
import com.example.backend.model.DAO.OptionDAO;
import com.example.backend.model.DAO.ProductDAO;
import com.example.backend.model.Product;
import org.jdbi.v3.core.Jdbi;


public class ProductService {
    Jdbi jdbi;
    private ProductDAO productDao;
    private ImageDao imageDao;
    private OptionDAO optionDao;

    public ProductService(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.productDao = jdbi.onDemand(ProductDAO.class);
        this.imageDao = jdbi.onDemand(ImageDao.class);
        this.optionDao = jdbi.onDemand(OptionDAO.class);
    }


    public ProductService() {
    }

    public List<Product> getProductsByCategory(int categoryId){
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getProductsByCategory(categoryId));
    }

    public Product getProductById(int productId){
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getProductById(productId));
    }


    public Product getProductByIdAndOptionId(int productId, int optionId){
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getProductByIdAndOptionId(productId,optionId));
    }

    public Product addProduct(Product product) {

        int productId =  productDao.addProduct( product.getName(), product.getSku(), product.getDescription(),
                product.getActive(), product.getCategoryId(), product.getBrandId(), product.getPrimaryImage());

        if (productId > 0) {
            product.setId(productId);
            return product;
        }
//        if (rowsAffected > 0) {
//            return productDao.getProductById(product.getId());  // Trả về sản phẩm đã được thêm vào
//        }

        return null;
    }



}
