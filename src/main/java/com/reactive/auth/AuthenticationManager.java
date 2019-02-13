package com.reactive.auth;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager extends UserDetailsRepositoryReactiveAuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManager.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Feel free to change userDetailsService with
     * {@link CustomUserDetailsService} instance here as per your custom
     * implementation.
     */
    @Autowired
    public AuthenticationManager(ReactiveUserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        // do other stuff like jwt checking
        LOGGER.info("Checking user authentication. Name: {}", authentication.getName());
        if (authentication.isAuthenticated()) {
            return Mono.just(authentication);
        }
        return super.authenticate(authentication);
    }

    @PostConstruct
    public void init() {
        setPasswordEncoder(passwordEncoder);
    }

}
