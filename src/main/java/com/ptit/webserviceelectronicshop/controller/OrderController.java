package com.ptit.webserviceelectronicshop.controller;

import com.ptit.webserviceelectronicshop.model.*;
import com.ptit.webserviceelectronicshop.model.request_body.OrderDTO;
import com.ptit.webserviceelectronicshop.repository.CartItemRepository;
import com.ptit.webserviceelectronicshop.repository.OrderItemRepository;
import com.ptit.webserviceelectronicshop.service.OrderService;
import com.ptit.webserviceelectronicshop.service.implement.CartServiceImpl;
import com.ptit.webserviceelectronicshop.service.implement.OrderServiceImpl;
import com.ptit.webserviceelectronicshop.service.implement.ProductServiceImpl;
import com.ptit.webserviceelectronicshop.service.implement.UserServiceImpl;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/order")
    public ResponseEntity<Object> createOrder(@NotNull @RequestBody @Valid OrderDTO body) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        User user = userService.getUserById(body.getUserId());
        if (user == null) {
            error.put("message", "User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        if(orderService.checkOrderByVnpayCode(body.getVnpayCode())){
            error.put("message", "Vnpay code is exist");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        Order order = new Order();
        orderService.saveOrder(order);
        List<OrderItem> list = new ArrayList<>();
        Long cartId = body.getCartId();
        List<CartItem> listCartItem = cartItemRepository.findByCartId(cartId);

        for (CartItem item : listCartItem) {
            for (Long idp : body.getProductIds()) {
                if (idp == item.getProduct().getId()) {

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(item.getProduct());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setOrder(order);

                    order.setTotal_quantity(order.getTotal_quantity() + item.getQuantity());
                    order.setTotal_price(order.getTotal_price() + item.getProduct().getPrice() * item.getQuantity());

                    orderItemRepository.save(orderItem);
                    list.add(orderItem);
                }
            }
        }

        order.setUser(user);
        order.setTotal_quantity(0);
        order.setTotal_price(0);
        order.setVnpayCode(body.getVnpayCode());
        order.setStatus(body.getStatus());

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = localDateTime.format(formatter);
        order.setPayment_at(formattedDateTime);
        orderService.updateOrder(order);
        response.put("message", "Order created successfully");
        response.put("order", order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long orderId) {
        return orderService.getOrder(orderId).orElse(null);
    }

    @GetMapping("/all")
    public List<Order> getAllOrder() {
        List<Order> list = orderService.getAllOrder();
        if (list.size() == 0) return null;
        return orderService.getAllOrder();
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
