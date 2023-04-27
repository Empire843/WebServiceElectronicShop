package com.ptit.webserviceelectronicshop.controller;

import com.ptit.webserviceelectronicshop.model.Order;
import com.ptit.webserviceelectronicshop.model.request_body.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
//    @Autowired
//    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO body) {

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long orderId) {

        return null;
    }
    @GetMapping("/all")
    public List<Order> getAllOrder() {

        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long orderId, @RequestBody OrderDTO body) {

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long orderId) {
        return null;
    }
}
