package com.ptit.webserviceelectronicshop.model.request_body;

import com.ptit.webserviceelectronicshop.model.Product;
import com.ptit.webserviceelectronicshop.model.User;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long cartId;
//    private int total_quantity;
//    private double total_price;
    private String status;
    private String vnpay_code;
    private List<Long> productIds;
}
