package com.ptit.webserviceelectronicshop.controller;

import com.ptit.webserviceelectronicshop.model.*;
import com.ptit.webserviceelectronicshop.model.request_body.OrderDTO;
import com.ptit.webserviceelectronicshop.repository.CartItemRepository;
import com.ptit.webserviceelectronicshop.repository.OrderItemRepository;
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
        User user = userService.getUserById(body.getId());
        if (user == null) {
            error.put("message", "User not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        Order order = new Order();
        orderService.saveOrder(order);
//        Cart cart = cartService.getCartByUserId(user.getId());
        List<OrderItem> list = new ArrayList<>();
        Long cartId = body.getCartId();
        List<CartItem> listCartItem = cartItemRepository.findByCartId(cartId);
        System.out.println(listCartItem + "-" + cartId);
        for (CartItem item : listCartItem) {
            for (Long idp : body.getProductIds()){
                if(idp == item.getProduct().getId()){
                    System.out.println(item);
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(item.getProduct());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setOrder(order);
                    order.setTotal_quantity(order.getTotal_quantity() + item.getQuantity());
                    System.out.println(orderItem);
                    orderItemRepository.save(orderItem);
                    list.add(orderItem);
                }
            }
        }

        order.setUser(user);
//        order.setItems(list);
        order.setTotal_quantity(0);
        order.setTotal_price(0);
        order.setVnpay_code(body.getVnpay_code());
        order.setStatus(body.getStatus());
        System.out.println(list);
//        orderItemRepository.saveAll(list);
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
