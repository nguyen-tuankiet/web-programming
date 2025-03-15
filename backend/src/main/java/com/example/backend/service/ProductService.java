package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.ProductDAO;
import com.example.backend.model.OptionVariant;
import com.example.backend.model.Product;
import com.example.backend.model.ProductDTO;
import org.jdbi.v3.core.Jdbi;

import javax.swing.plaf.PanelUI;
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


    public ProductDTO editProductById(int id) {
        Product product = productDao.editProduct(id);
        List<OptionVariant> variants = productDao.getVariants(id);
        return new ProductDTO(product, variants);
    }


    public Product getProductByIdAndOptionId(int productId, int optionId){
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getProductByIdAndOptionId(productId,optionId));
    }

    public List<Product> getAllProducts() {
        List<Product> products = jdbi.withExtension(ProductDAO.class, dao -> dao.getAllProducts());

        return products;
    }

    public Integer getMinimumPriceForProduct(int productId) {
        return productDao.getMinimumPriceForProduct(productId);
    }

    public Integer getPriceForOption(int optionId) {
        return productDao.getPriceForOption(optionId);
    }


    public Product addProduct(Product product) {

        String generatedSku = "HKS-" + System.currentTimeMillis();
        product.setSku(generatedSku);

        int productId = productDao.addProduct(product.getName(), product.getDescription(),
                product.getActive(), product.getCategoryId(), product.getBrandId(), product.getPrimaryImage(), product.getSku());

        if (productId > 0) {
            product.setId(productId);
            return product;
        }
//        if (rowsAffected > 0) {
//            return productDao.getProductById(product.getId());  // Trả về sản phẩm đã được thêm vào
//        }

        return null;
    }

    public List<Product> searchProducts(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be empty");
        }
        String keyword = "%" + name + "%";
        return productDao.searchProducts(keyword);
    }

    public List<Product> searchProducts(String name , int limit) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be empty");
        }
        String keyword = "%" + name + "%";
        return productDao.searchProducts(keyword, limit);
    }



    public List<Product> getTopProductsByCategory(Integer categoryId, Integer limit) {
        if (categoryId <= 0 || limit <= 0) {
            throw new IllegalArgumentException("Bad request");
        } else {
            return productDao.getTopProductsByCategoryId(categoryId, limit);
        }
    }


    public Boolean increaseNoOfViews(Integer productId) {
        return productDao.increaseNoOfViews(productId);
    }


    public Boolean increaseNoOfSold(Integer productId, Integer quantity) {
        return productDao.increaseNoOfSold(productId, quantity );
    }


    public List<Product> getTop10(){
        return productDao.getTop10();
    }


    public static void main(String[] args) {
        ProductService productService = new ProductService(DBConnection.getJdbi());
//        System.out.println(productService.getTopProductsByCategory(1, 3));
//        System.out.println(productService.increaseNoOfViews(1));
//        System.out.println(productService.increaseNoOfSold(10, 5));
        System.out.println(productService.getTop10( ).toString());
    }
}
