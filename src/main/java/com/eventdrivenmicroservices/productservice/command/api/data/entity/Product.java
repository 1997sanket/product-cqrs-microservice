package com.eventdrivenmicroservices.productservice.command.api.data.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

}
