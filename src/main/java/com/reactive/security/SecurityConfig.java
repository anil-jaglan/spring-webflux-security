package com.reactive.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Import(value = SecurityServiceConfig.class)
public class SecurityConfig {

    @Autowired
    private ReactiveAuthenticationManager authManager;

    @Autowired
    private ServerSecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
       //@formatter:off
       return  http
               .csrf().disable()
               .authenticationManager(authManager)
               .securityContextRepository(securityContextRepository)
               .authorizeExchange().pathMatchers("/public", "/public/**").permitAll()
               .pathMatchers("/api/**").authenticated()
               //.pathMatchers("/api/order/**").hasRole("USER")  // this also works.
               //.pathMatchers("/api/wallet/**").hasRole("ADMIN")
               .and()
               .httpBasic()
               .and()
               .build();
     //@formatter:on
    }

}
