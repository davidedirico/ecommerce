package com.example.ecommerce.application.repository;

import com.example.ecommerce.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
