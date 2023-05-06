package com.ptit.webserviceelectronicshop.repository;

import com.ptit.webserviceelectronicshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
<<<<<<< Updated upstream
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
=======

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    @Query("SELECT c FROM Product c WHERE c.name LIKE %:keyword%")
    ArrayList<Product> findByKey(@Param("keyword") String keyword);
>>>>>>> Stashed changes
}
