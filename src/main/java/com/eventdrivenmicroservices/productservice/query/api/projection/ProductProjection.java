package com.eventdrivenmicroservices.productservice.query.api.projection;

import com.eventdrivenmicroservices.productservice.command.api.data.entity.Product;
import com.eventdrivenmicroservices.productservice.command.api.model.ProductRestModel;
import com.eventdrivenmicroservices.productservice.query.api.queries.GetProductsQuery;

import com.eventdrivenmicroservices.productservice.query.api.repository.QueryProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    @Autowired
    private QueryProductRepository queryProductRepository;

    /*
        We could have also created an event from here and let the event send us the data.
     */
    @QueryHandler
    public List<ProductRestModel> handler(GetProductsQuery getProductsQuery) {
        List<Product> products = queryProductRepository.findAll();

        List<ProductRestModel> productRestModels = products.stream().map(p->ProductRestModel.builder()
                .name(p.getName())
                .price(p.getPrice())
                .quantity(p.getQuantity())
                .build()).collect(Collectors.toList());

        return productRestModels;
    }

}
