package com.eventdrivenmicroservices.productservice.command.api.data.repository;

import com.eventdrivenmicroservices.productservice.command.api.data.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
