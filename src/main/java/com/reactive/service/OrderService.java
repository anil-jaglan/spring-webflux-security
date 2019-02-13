package com.reactive.service;

import com.reactive.domain.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private static final Map<Integer, Order> orders = new HashMap<>();
    static {
        IntStream.range(1, 100).forEach(
                i -> orders.put(i, new Order(i, "Customer-" + i, "Address-" + i, 10 * i, 1 + i)));
    }

    public Mono<Order> getOrder(int orderId) {
        return Mono.just(orders.get(orderId));
    }

    public Flux<Order> getAllOrder() {
        return Flux.fromIterable(orders.values());
    }
}
