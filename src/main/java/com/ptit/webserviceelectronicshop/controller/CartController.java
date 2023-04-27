package com.ptit.webserviceelectronicshop.controller;

import com.ptit.webserviceelectronicshop.model.Cart;
import com.ptit.webserviceelectronicshop.model.request_body.CartDTO;
import com.ptit.webserviceelectronicshop.service.implement.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartServiceImpl service;

    @PostMapping("/createCart")
    public ResponseEntity<?> createCart(@RequestBody CartDTO body) {

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable("id") Long cartId) {

        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable("id") Long cartId, @RequestBody CartDTO body) {

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long cartId) {

        return null;
    }
}
