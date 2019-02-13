package com.reactive.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityServiceConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * THIS BEAN IS TEMPORARY FOR KEEPING USER AUTHORIZATION IN-MEMORY AND IT
     * CAN BE REPLACED BY {@link CustomUserDetailsService} class instance.
     */
    @Bean
    public MapReactiveUserDetailsService userDetailService() {
        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("user")).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles(
                "ADMIN").build();

        return new MapReactiveUserDetailsService(user, admin);
    }
}
