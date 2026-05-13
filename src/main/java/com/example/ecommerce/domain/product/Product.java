package com.example.ecommerce.domain.product;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    protected Product() {} //richiesto da JPA

    //campi di dominio - appartengono solo a Product
    @Column (nullable = false)
    private String name;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private Category category;

    @Column (nullable = false)
    private BigDecimal unitPrice;

    @Column (nullable = false)
    private int availableQuantity;

    @Column (nullable = false, updatable = false)
    private Instant createdAt;

    @Column (nullable = false)
    private Instant updatedAt;

    @Column (nullable = false)
    private boolean active;

    @Version
    private Long version;


    // --costruttore di dominio--
    public Product (
            String name,
            Category category,
            BigDecimal unitPrice,
            int availableQuantity
    ) {
        this.updatedAt = Instant.now();
        this.createdAt = Instant.now();
        this.name = Objects.requireNonNull(name);
        this.category = Objects.requireNonNull(category);
        this.unitPrice = Objects.requireNonNull(unitPrice);
        if (availableQuantity < 0) {
           throw new IllegalArgumentException("quantity must be positive integer");
        }
        if (unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("UnitPrice must be greater than zero");
        }
    }

    //--comportamenti di dominio--

    public void deactivate () {
        this.active = false;
    }


    public void changePrice(BigDecimal newPrice) {
        Objects.requireNonNull(newPrice);

        if (newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be >= 0");
        }

        this.unitPrice = newPrice;
    }

    public void decreaseStock (int quantity) {
     if (quantity<0) {
         throw new IllegalArgumentException("quantoty must be greater than 0");
     }

     if (availableQuantity < quantity) {
            throw new IllegalStateException("Not enough stock");
        }
     this.availableQuantity -= quantity;

    }


    @PreUpdate
    void onUpdate () {
        this.updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getName() {
        return name;
    }
}
