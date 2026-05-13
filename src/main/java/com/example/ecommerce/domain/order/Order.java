package com.example.ecommerce.domain.order;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identità esterna (utente)
    @Column(nullable = false)
    private Long userId;

    // Stato del processo
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    // Riferimento al pagamento (id esterno)
    private Long paymentId;

    // Audit
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<OrderItem> items = new ArrayList<>();

    @Version
    private Long version;

    protected Order() {
        // richiesto da JPA
    }

    public Order(Long userId) {
        this.userId = Objects.requireNonNull(userId);
        this.status = OrderStatus.PENDING_PAYMENT;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    // -----------------
    // Comportamento
    // -----------------

    public void markPaid (Long paymentId) {
        if(this.status != OrderStatus.PENDING_PAYMENT)
            return; //idempotente
        this.paymentId = paymentId;
        this.status = OrderStatus.PAID;
    }

    public void cancel() {
        if (this.status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot cancel a delivered order");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public void addItem (OrderItem item) {
        if (!isModified()) {
            throw new IllegalStateException("Order is not modified");
        }
        item.attachTo(this);
        this.items.add(item);
    }

    public void removeItem (OrderItem item){
        if (!isModified()) {
            throw new IllegalStateException("Order is not modified");
        }
        items.remove(item);
        item.attachTo(null);
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }

    private boolean isModified () {
        return this.status == OrderStatus.PENDING_PAYMENT;
    }

    @Transient
    public BigDecimal getTotalPrice () {
        return items.stream()
                .map(or -> or.getTotalprice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }


// getter essenziali (puoi generarli con IntelliJ)
}