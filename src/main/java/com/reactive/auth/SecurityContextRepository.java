package com.reactive.auth;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * 
 * Class to create user authentication context before processing a request.
 *
 */
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityContextRepository.class);

    @Autowired
    private ReactiveAuthenticationManager reactiveAuthenticationManager;

    private static final String BASIC_AUTH_PREFIX = "Basic";

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);       
        /*
         * Extracting Basic user Authorization from header, decoding it and
         * authenticating user manually.
         * 
         */
        if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(BASIC_AUTH_PREFIX)) {
            LOGGER.info("Authorization header found : {}", authHeader);
            authHeader = new String(
                    Base64.getDecoder().decode(authHeader.substring(BASIC_AUTH_PREFIX.length()).trim()),
                    StandardCharsets.UTF_8);
            LOGGER.info("Decoded basic auth: {}", authHeader);
            final String[] loginRequest = authHeader.split(":");
            Mono<Authentication> authentication = reactiveAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest[0], loginRequest[1]));
            return authentication.map(auth -> (SecurityContext) new SecurityContextImpl(auth));
        }
        return Mono.empty();
        
    }

}
