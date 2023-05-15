package com.ptit.webserviceelectronicshop.model.request_body;

import lombok.Data;

@Data
public class RevenueStatisticsDTO {
    private long id;
    private String productName;
    private String categoryName;
    private long totalQuantity;
    private double totalPrice;

    public RevenueStatisticsDTO(long id, String productName, String categoryName, long totalQuantity, double totalPrice) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public RevenueStatisticsDTO(String productName, long totalQuantity, double totalPrice) {
        this.productName = productName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public RevenueStatisticsDTO(String productName, String categoryName, long totalQuantity, double totalPrice) {
        this.productName = productName;
        this.categoryName = categoryName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }
}
