package com.example.ecommerce.api.dto.request;

public class AddItemRequest {

    private Long productId;
    private int quantity;

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
