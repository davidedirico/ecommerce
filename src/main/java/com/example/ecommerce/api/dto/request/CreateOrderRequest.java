package com.example.ecommerce.api.dto.request;

//rappresenta il payload HTTP
public class CreateOrderRequest {

    private Long userId;
    private Long productId;
    private int quantity;

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
