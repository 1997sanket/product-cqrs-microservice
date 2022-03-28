package com.eventdrivenmicroservices.productservice.command.api.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRestModel {

    private String name;
    private BigDecimal price;
    private Integer quantity;
}
