package com.shoponline.service;

import com.shoponline.domain.Product;

import java.util.List;


public interface ProductService {
    Product getProduct(int id);

    Product getProduct(String name);

    void addProduct(Product product);

    boolean deleteProduct(int id);

    boolean updateProduct(Product product);

    List<Product> getProductsByKeyWord(String searchKeyWord);

    List<Product> getProductsByType(int type);

    List<Product> getAllProduct();
}
