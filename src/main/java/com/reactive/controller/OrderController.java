package com.reactive.controller;

import com.reactive.domain.Order;
import com.reactive.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/api/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Order> getOrder(@PathVariable("id") int id) {

        return orderService.getOrder(id);

    }

}
