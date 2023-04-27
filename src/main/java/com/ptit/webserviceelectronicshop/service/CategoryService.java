package com.ptit.webserviceelectronicshop.service;

import com.ptit.webserviceelectronicshop.model.Category;

public interface CategoryService {
    boolean addNewCategory(Category category);
    Category updateCategory(Long id, Category category);
    boolean deleteCategory(Long id);
}
