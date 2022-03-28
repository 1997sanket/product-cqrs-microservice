package com.eventdrivenmicroservices.productservice.command.api.command;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductCommand {

    @TargetAggregateIdentifier
    private String productId;

    private String name;
    private BigDecimal price;
    private Integer quantity;

}
