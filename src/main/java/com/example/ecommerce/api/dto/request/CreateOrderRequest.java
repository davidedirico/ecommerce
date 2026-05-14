package com.example.ecommerce.api.dto.request;

//rappresenta il payload HTTP
public class CreateOrderRequest {

    private Long userId;
    private Long productId;
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


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
