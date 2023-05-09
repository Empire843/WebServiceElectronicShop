package com.ptit.webserviceelectronicshop.service.implement;

import com.ptit.webserviceelectronicshop.model.Cart;
import com.ptit.webserviceelectronicshop.repository.CartRepository;
import com.ptit.webserviceelectronicshop.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository repository;
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    @Override
    public void saveCartWithItems(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void addtoCart(Cart cart) {
        repository.save(cart);
    }

    @Override
    public Cart getCartById(Long id) {
        return repository.findCartById(id);
    }

    @Override
    public void saveCart(Cart cart) {
        repository.save(cart);
    }

    @Override
    public Cart getCartByUserId(Long id) {
        return repository.findByUserId(id);
    }
}
