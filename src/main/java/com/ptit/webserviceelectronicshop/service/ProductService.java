package com.ptit.webserviceelectronicshop.service;

import com.ptit.webserviceelectronicshop.model.Category;
import com.ptit.webserviceelectronicshop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(Product product);
    List<Product> getAllProduct();
    boolean deleteProduct(Long id);
    Optional<Product> getProductById(Long id);

    List<Product> getProductByCategory(Category category);

    ArrayList<Product> getProductsContainKeys(String key);

    ArrayList<Product> getProductsByCategory(Long id);

    ArrayList<Product> getProductInSpacePrice(Double start, Double end);

    ArrayList<Product> getProductsByIds(List<Long> selectedIds);
}
