package com.ptit.webserviceelectronicshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptit.webserviceelectronicshop.model.Cart;
import com.ptit.webserviceelectronicshop.model.CartItem;
import com.ptit.webserviceelectronicshop.model.Product;
import com.ptit.webserviceelectronicshop.model.User;
import com.ptit.webserviceelectronicshop.model.request_body.Cart.CartBody;
import com.ptit.webserviceelectronicshop.model.request_body.Cart.CartItemBody;
import com.ptit.webserviceelectronicshop.model.request_body.Cart.CartItemDTO;
import com.ptit.webserviceelectronicshop.repository.CartItemRepository;
import com.ptit.webserviceelectronicshop.repository.CartRepository;
import com.ptit.webserviceelectronicshop.repository.ProductRepository;
import com.ptit.webserviceelectronicshop.service.UserService;
import com.ptit.webserviceelectronicshop.service.implement.CartServiceImpl;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/add-item")
    public ResponseEntity<?> addToCart(@RequestBody CartBody body) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        User user = userService.getUserById(body.getUserId());
        Cart cart = user.getCart();
        List<CartItem> list = new ArrayList<>();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setTotalAmount(0L);
            cartService.saveCart(cart);
        }
        list = cartItemRepository.findByCartId(cart.getId());
        for (CartItem item : list) {
            if (item.getProduct().getId() == body.getProductId()) {
                cart.setTotalAmount(cart.getTotalAmount() + (long) (item.getProduct().getPrice() * body.getQuantity()));
                item.setQuantity(item.getQuantity() + body.getQuantity());
                item.setCart(cart);
                cartService.saveCartWithItems(cart);
                response.put("cartId", cart.getId());
                response.put("message", "The quantity of product updated!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        Product product = productRepository.findById(body.getProductId()).orElse(null);
        if (product == null) {

            error.put("message", "Product id is not valid");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        // add to Cart Item
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(body.getQuantity());
        cartItem.setCart(cart);
        list.add(cartItem);
        cartItemRepository.save(cartItem);

        cart.setTotalAmount(cart.getTotalAmount() + (long) (product.getPrice() * body.getQuantity()));
        cartService.saveCartWithItems(cart);
        response.put("cartId", cart.getId());
        response.put("message", "added to cart successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/items")
    public List<CartItem> getCartItemsByCartId(@PathVariable Long id) {
        return cartItemRepository.findByCartId(id);
    }
}
