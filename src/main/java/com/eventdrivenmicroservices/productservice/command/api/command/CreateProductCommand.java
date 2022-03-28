package com.eventdrivenmicroservices.productservice.command.api.command;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductCommand {

    // So that the Aggregate and Command uniquely identifies the product and
    // communicates
    @TargetAggregateIdentifier
    private String productId;

    // Payload will be same as the REST model
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
