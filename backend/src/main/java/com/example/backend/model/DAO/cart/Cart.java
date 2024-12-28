package com.example.backend.model.DAO.cart;

import com.example.backend.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Integer, ProductCart> data = new HashMap<>();

    public boolean addProduct(Product product, Integer optionId, String imageUrl) {
        if (data.containsKey(product.getId())) {
            return update(product, data.get(product.getId()).getQuantity() + 1);
        }
        else {
            data.put(product.getId(), new ProductCart(product, optionId, imageUrl));
            return true;
        }

    }



    public boolean update(Product product, int quantity) {
        if (data.containsKey(product.getId())) {
            ProductCart productCart = data.get(product.getId());
            productCart.setQuantity(quantity);
            data.put(product.getId(), productCart);
            return true;
        }
        return false;
    }







}
