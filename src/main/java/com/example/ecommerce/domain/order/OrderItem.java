package com.example.ecommerce.domain.order;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@Entity
@Table (name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // -- riferimento tecnico al prodotto--
    @Column (nullable = false)
    private Long productId;

    @Column (nullable = false)
    private String productName;

    @Column (nullable = false)
    private BigDecimal priceAtPurchase;

    // -- dati riga --
    @Column (nullable = false)
    private Integer quantity;

    protected OrderItem () {}; //richiesto da jpa

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem (
            Long productId,
            String productName,
            BigDecimal priceAtPurchase,
            Integer quantity
    ) {
        this.productId = productId;
        this.priceAtPurchase = priceAtPurchase;
        this.productName = productName;
        if(quantity<=0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
    }

    void attachTo (Order order){
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public String getProductName() {
        return productName;
    }

    public Long getProductId() {
        return productId;
    }

    public int getId() {
        return id;
    }

    @Transient
    public BigDecimal getTotalprice(){
        return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
    };

}
