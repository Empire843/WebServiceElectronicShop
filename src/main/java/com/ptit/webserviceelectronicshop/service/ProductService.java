package com.ptit.webserviceelectronicshop.service;

import com.ptit.webserviceelectronicshop.model.Category;
import com.ptit.webserviceelectronicshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(Product product);

    List<Product> getAllProduct();

    boolean deleteProduct(Long id);

    Optional<Product> getProductById(Long id);

    void updateProduct(Product product);

    List<Product> getProductByCategory(Category category);

    void updateProductQuantity(Long productId, int newQuantity);
}
