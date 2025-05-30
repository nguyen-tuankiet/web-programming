package com.example.backend.controller.Products;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Options;
import com.example.backend.model.Product;
import com.example.backend.service.ImageService;
import com.example.backend.service.OptionService;
import com.example.backend.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ProductDetailController", value = "/product-detail")
public class ProductDetailController extends HttpServlet {
    ProductService productService = new ProductService(DBConnection.getJdbi());
    ImageService imageService = new ImageService(DBConnection.getJdbi());
    OptionService optionService = new OptionService(DBConnection.getJdbi());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(productId);

        Integer productPrice = productService.getMinimumPriceForProduct(productId); // Default to minimum price
        if (product.getOptionId() != null) {
            productPrice = productService.getPriceForOption(product.getOptionId());
        }



        List<String> images = imageService.getAllImagesByProductId(product.getId());
        String primaryImageUrl = imageService.getImageUrlById(product.getPrimaryImage());
        List<String> descriptions = List.of(product.getDescription().split("\\n"));


        List<Options> options = optionService.getOptionsByProductId(product.getId());
        List<Integer> optionIds = options.stream().map(Options::getId).collect(Collectors.toList());

        List<Options> optionVariant = optionService.getVariantByOptionId(optionIds);
        List<String> varaints = optionVariant.stream().map(Options::getVariantName).distinct().collect(Collectors.toList());



        request.setAttribute("images", images);
        request.setAttribute("primaryImageUrl", primaryImageUrl); // Add primary image URL
        request.setAttribute("product", product);
        request.setAttribute("descriptions", descriptions);
        request.setAttribute("productPrice", productPrice);
        request.setAttribute("optionVariant", optionVariant);
        request.setAttribute("varaints", varaints);


        productService.increaseNoOfViews(productId);

        request.getRequestDispatcher("product_detail/ProductDetail.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}