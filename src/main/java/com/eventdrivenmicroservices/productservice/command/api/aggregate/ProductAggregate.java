package com.eventdrivenmicroservices.productservice.command.api.aggregate;

import java.math.BigDecimal;

import com.eventdrivenmicroservices.productservice.command.api.command.CreateProductCommand;
import com.eventdrivenmicroservices.productservice.command.api.command.DeleteProductCommand;
import com.eventdrivenmicroservices.productservice.command.api.event.ProductCreatedEvent;
import com.eventdrivenmicroservices.productservice.command.api.event.ProductDeletedEvent;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Aggregate // Stereotype annotation
public class ProductAggregate {

    @AggregateIdentifier // To commnicate with the Command
    private String productId;

    // Payload will be same as the REST model
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate() {

    }

    // We need this Constructor, because our CreateProductCommand is injected
    // here
    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {

        // We can perform validations here

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        // Copies all the values from createProductCommand to productCreatedEvent,
        // easier syntax than Builder (because all the field names are same)
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        // Publish this event
        AggregateLifecycle.apply(productCreatedEvent);

    }

    @CommandHandler
    public void deleteProductCommandHandler(DeleteProductCommand deleteProductCommand) {

        ProductDeletedEvent productDeletedEvent = new ProductDeletedEvent();
        BeanUtils.copyProperties(deleteProductCommand, productDeletedEvent);

        // Publishing the event
        AggregateLifecycle.apply(productDeletedEvent);
    }

    // Don't exactly know why this is done. Something about changing the state of
    // the Aggregate
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.quantity = productCreatedEvent.getQuantity();
        this.productId = productCreatedEvent.getProductId();
        this.price = productCreatedEvent.getPrice();
        this.name = productCreatedEvent.getName();
    }

    @EventSourcingHandler
    public void on(ProductDeletedEvent productDeletedEvent) {
        this.quantity = productDeletedEvent.getQuantity();
        this.productId = productDeletedEvent.getProductId();
        this.price = productDeletedEvent.getPrice();
        this.name = productDeletedEvent.getName();
    }
}
