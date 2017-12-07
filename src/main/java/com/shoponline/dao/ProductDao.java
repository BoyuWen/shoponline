package com.shoponline.dao;

import com.shoponline.domain.Product;

import java.util.List;


public interface ProductDao {
    public Product getProduct(int id);

    public Product getProduct(String name);

    public void addProduct(Product product);

    public boolean deleteProduct(int id);

    public boolean updateProduct(Product product);

    public List<Product> getProductsByKeyWord(String searchKeyWord);

    public List<Product> getProductsByType(int type);

    public List<Product> getAllProduct();
}
