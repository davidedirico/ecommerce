package com.example.ecommerce.api.dto.response;

public class OrderResponseError {

    private String errorText;

    public String getErrorText() {
        return errorText;
    }

    public OrderResponseError(String errorText) {
        this.errorText = errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
