package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.ProductDAO;
import com.example.backend.model.Product;
import org.jdbi.v3.core.Jdbi;

import java.util.List;


public class ProductService {
    Jdbi jdbi;
    private ProductDAO productDao;

    public ProductService(Jdbi jdbi) {
        this.jdbi = jdbi;
        this.productDao = jdbi.onDemand(ProductDAO.class);
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

    public List<Product> getAllProducts() {
        List<Product>  products = jdbi.withExtension(ProductDAO.class, dao -> dao.getAllProducts());

        return products;
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


    public static void main(String[] args) {
        ProductService productService = new ProductService(DBConnection.getJdbi());
        System.out.println(productService.getAllProducts());
    }
}
