package com.reactive.filter;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
@Order(value=99)
public class YFilter implements WebFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(YFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        LOGGER.info("Passing through YFilter at: {}", LocalDateTime.now());
        return chain.filter(exchange);
    }

}