package com.ptit.webserviceelectronicshop.model.request_body;

import com.ptit.webserviceelectronicshop.model.Product;
import com.ptit.webserviceelectronicshop.model.User;
import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private double price;
    private String status;
}
