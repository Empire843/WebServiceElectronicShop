package com.ptit.webserviceelectronicshop.repository;

import com.ptit.webserviceelectronicshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCode(String code);

    @Query("SELECT c FROM Category c WHERE c.name LIKE %:keyword%")
    ArrayList<Category> getCategoryByKey(@Param("keyword") String keyword);
}
