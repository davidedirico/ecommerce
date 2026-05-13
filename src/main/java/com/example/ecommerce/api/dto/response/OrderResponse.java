package com.example.ecommerce.api.dto.response;

import com.example.ecommerce.domain.order.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {

    private Long orderId;

    private Long userId;
    private String status;
    private BigDecimal totalPrice;
    private List<OrderItemResponse> items;

    public OrderResponse(List<OrderItemResponse> items, BigDecimal totalPrice, String status, Long userId, Long orderId) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }
}
