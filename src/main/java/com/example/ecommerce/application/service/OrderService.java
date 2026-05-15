package com.example.ecommerce.application.service;


import com.example.ecommerce.application.repository.OrderRepository;
import com.example.ecommerce.application.repository.ProductRepository;
import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.order.OrderItem;
import com.example.ecommerce.domain.product.Product;
import com.example.ecommerce.exception.InvalidProductException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    public Order createOrder(Long userId,
                            Long productId,
                            int quantity){

        Product product = productRepository.findById(productId).orElseThrow(() -> new InvalidProductException());

        product.decreaseStock(quantity);
        OrderItem orderItem = new OrderItem(product.getId(), product.getName(), product.getUnitPrice(), quantity);

        Order order = new Order(userId);
        order.addItem(orderItem);

        return orderRepository.save(order);

    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("order "+ id + "does not exists"));
    }
}
