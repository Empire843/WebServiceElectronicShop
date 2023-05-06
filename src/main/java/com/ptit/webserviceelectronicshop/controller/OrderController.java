package com.ptit.webserviceelectronicshop.controller;

import com.ptit.webserviceelectronicshop.model.Order;
import com.ptit.webserviceelectronicshop.model.Product;
import com.ptit.webserviceelectronicshop.model.User;
import com.ptit.webserviceelectronicshop.model.request_body.OrderDTO;
import com.ptit.webserviceelectronicshop.service.implement.OrderServiceImpl;
import com.ptit.webserviceelectronicshop.service.implement.ProductServiceImpl;
import com.ptit.webserviceelectronicshop.service.implement.UserServiceImpl;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {
//    @Autowired
//    private OrderService orderService;
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProductServiceImpl productService;

//    {
//            "userId": 1,
//            "productId": 2,
//            "quantity": 2,
//            "price": 5000,
//            "status": "new"
//    }
    @PostMapping("/save")
    public ResponseEntity<Object> createOrder(@NotNull @RequestBody @Valid OrderDTO orderDTO, BindingResult bindingResult) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();

        if (bindingResult.hasErrors()) {
            for (FieldError error1 : bindingResult.getFieldErrors()) {
                error.put(error1.getField(), error1.getDefaultMessage());
            }
            return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
        }

        try {
            Order order = new Order();
            order.setPrice(orderDTO.getPrice());
            order.setStatus(orderDTO.getStatus());
            order.setQuantity(orderDTO.getQuantity());
            order.setUser(this.userService.getUserById(orderDTO.getUserId()));
            order.setProduct(this.productService.getProductById(orderDTO.getProductId()).get());

            this.orderService.addOrder(order);
        }
        catch (Exception e) {
            error.put("message", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
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
