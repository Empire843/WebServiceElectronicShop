package com.ptit.webserviceelectronicshop.service.implement;

import com.ptit.webserviceelectronicshop.model.Order;
import com.ptit.webserviceelectronicshop.repository.OrderRepository;
import com.ptit.webserviceelectronicshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@EnableJpaRepositories(basePackageClasses = OrderRepository.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    @Override
    public Order addOrder(Order order) {
        //Optional<Order> savedOrder = Optional.of(this.orderRepository.save(order));
        return this.orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
}
