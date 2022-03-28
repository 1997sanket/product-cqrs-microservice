package com.eventdrivenmicroservices.productservice.command.api.controller;

import java.util.List;
import java.util.UUID;

import com.eventdrivenmicroservices.productservice.command.api.command.CreateProductCommand;
import com.eventdrivenmicroservices.productservice.command.api.command.DeleteProductCommand;
import com.eventdrivenmicroservices.productservice.command.api.data.entity.Product;
import com.eventdrivenmicroservices.productservice.command.api.model.ProductRestModel;

import com.eventdrivenmicroservices.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCommandController {

    private CommandGateway commandGateway;


    // Constructor injection
    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello CQRS";
    }

    @PostMapping("/products")
    public String addProduct(@RequestBody ProductRestModel productRestModel) {

        // 1. Map this product to CreateProductCommand.
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(productRestModel.getName())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();

        // 2. Send this Command to Command Gateway
        String result = commandGateway.sendAndWait(createProductCommand);

        return result;

    }


    @DeleteMapping("/products")
    public String deleteProduct(@RequestBody Product product) {

        // Create a Command
        DeleteProductCommand deleteProductCommand = new DeleteProductCommand();

        BeanUtils.copyProperties(product, deleteProductCommand);

        // Send this Command to CommandGateway
        String result = commandGateway.sendAndWait(deleteProductCommand);

        return result;
    }

}
