package com.eventdrivenmicroservices.productservice.query.api.repository;

import com.eventdrivenmicroservices.productservice.command.api.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryProductRepository extends JpaRepository<Product, String> {
}
