package com.example.ecommerce.api.controller;


import com.example.ecommerce.api.dto.request.CreateOrderRequest;
import com.example.ecommerce.api.dto.response.OrderItemResponse;
import com.example.ecommerce.api.dto.response.OrderResponse;
import com.example.ecommerce.application.service.OrderService;
import com.example.ecommerce.domain.order.Order;
import com.example.ecommerce.domain.order.OrderItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder (@RequestBody CreateOrderRequest orderRequest) {
        Order order =  orderService.createOrder(orderRequest.getUserId(),orderRequest.getProductId(),orderRequest.getQuantity());
        return ResponseEntity.ok(toResponse(order));
    }

    private OrderResponse toResponse(Order order){
         OrderResponse orderResponse = new OrderResponse(
                order.getItems().stream()
                        .map(this::toItemResponse)
                        .collect(Collectors.toList()),
                order.getTotalPrice(),
                order.getStatus().name(),
                order.getUserId(),
                order.getId());

         return orderResponse;
    }

    private OrderItemResponse toItemResponse (OrderItem orderItem){
         OrderItemResponse orderItemResponse = new OrderItemResponse(orderItem.getProductId(), orderItem.getProductName(), orderItem.getTotalprice(), orderItem.getQuantity());
         return orderItemResponse;
    }

}
