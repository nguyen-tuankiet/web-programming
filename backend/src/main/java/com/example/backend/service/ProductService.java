package com.example.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.example.backend.model.DAO.ImageDao;
import com.example.backend.model.DAO.ProductDAO;
import com.example.backend.model.Product;
import org.jdbi.v3.core.Jdbi;


public class ProductService {
    Jdbi jdbi;
    private  ProductDAO productDAO;
    private  ImageDao imageDao;


    public ProductService(Jdbi jdbi, ProductDAO productDAO, ImageDao imageDao){
        this.jdbi = jdbi;
        this.productDAO = jdbi.onDemand(ProductDAO.class);
        this.imageDao = imageDao;
    }

    public ProductService(Jdbi jdbi){
        this.jdbi = jdbi;
    }
    public List<Product> getProductsByCategory(int categoryId){
        return jdbi.withExtension(ProductDAO.class, dao -> dao.getProductsByCategory(categoryId));
    }

    public Product addProduct(Product product, List<String> imageUrls) {
        // Lưu hình ảnh và nhận ID cho mỗi ảnh
        List<Integer> imageIds = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            long imageId = imageDao.saveImage(imageUrl);
            imageIds.add((int) imageId);  // Chuyển đổi imageId từ long thành Integer
        }

        // Lưu sản phẩm
        long productId = productDAO.addProduct(product.getName(), product.getSku(), product.getDescription(),
                product.getIsActive(), product.getCategoryId(), product.getBrandId(),
                product.getNoOfViews(), product.getNoOfSold(),
                product.getPrimaryImage(), product.getPrice());

        // Liên kết sản phẩm với hình ảnh
        for (Integer imageId : imageIds) {
            productDAO.linkProductToImage(productId, imageId);
        }

        // Cập nhật hình ảnh chính của sản phẩm nếu có
        if (!imageIds.isEmpty()) {
            productDAO.updatePrimaryImage(productId, imageIds.get(0));  // Giả sử hình ảnh đầu tiên là hình ảnh chính
        }

        // Trả về sản phẩm đã thêm với ID
        return new Product((int) productId, product.getName(), product.getSku(), product.getDescription(),
                product.getIsActive(), product.getCategoryId(), product.getBrandId(),
                product.getNoOfViews(), product.getNoOfSold(), imageIds.get(0), product.getPrice());
    }
}
