package com.eventdrivenmicroservices.productservice.query.api.controller;

import com.eventdrivenmicroservices.productservice.command.api.data.entity.Product;
import com.eventdrivenmicroservices.productservice.command.api.model.ProductRestModel;
import com.eventdrivenmicroservices.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
    Normall we will have seperate database and micro-service for queries
 */
@RestController
public class ProductQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping("/products")
    public List<ProductRestModel> getProducts() {

        GetProductsQuery getProductsQuery = new GetProductsQuery();
        List<ProductRestModel> productRestModels = queryGateway
                .query(getProductsQuery,
                        ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                                .join();

        return productRestModels;
    }
}
