package com.ptit.webserviceelectronicshop.model.request_body.Product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private Long category_id;
    private String name;
    private Double price;
    private String description;
    private LocalDateTime created_date;
    private int quantity;
    private String image;
}
