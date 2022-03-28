package com.eventdrivenmicroservices.productservice.command.api.event;

import com.eventdrivenmicroservices.productservice.command.api.data.entity.Product;
import com.eventdrivenmicroservices.productservice.command.api.data.repository.ProductRepository;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // This is our our event handler which will save the data in database using
    // repository layer
    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {

        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent, product);

        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductDeletedEvent productDeletedEvent) {

        Product product = new Product();
        BeanUtils.copyProperties(productDeletedEvent, product);

        productRepository.delete(product);
    }

}
