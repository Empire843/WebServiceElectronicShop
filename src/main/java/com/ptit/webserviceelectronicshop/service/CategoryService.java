package com.ptit.webserviceelectronicshop.service;

import com.ptit.webserviceelectronicshop.model.Category;

import java.util.ArrayList;

public interface CategoryService {
    boolean addNewCategory(Category category);
    Category updateCategory(Long id, Category category);
    boolean deleteCategory(Long id);

    ArrayList<Category> getCategoryContainKey(String key);
}
