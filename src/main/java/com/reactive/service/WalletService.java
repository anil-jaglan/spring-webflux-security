package com.reactive.service;

import com.reactive.domain.Wallet;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class WalletService {

    private static final Wallet wallet = new Wallet();

    public Mono<Float> getBalance() {
        return Mono.just(wallet.getBalance());
    }
    
    @PreAuthorize(value="hasRole('ADMIN')")
    public Mono<Float> addMoney(float amount) {
        return Mono.just(wallet.add(amount));
    }

    public Mono<Float> pay(float amount) {
        return Mono.just(wallet.pay(amount));
    }

}
