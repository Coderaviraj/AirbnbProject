package com.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // Configure Spring Security here.
    // For example, you can enable basic authentication and add custom login/logout endpoints.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //h(cd)^2
        http.csrf().disable().cors().disable();
    //haas.configure
        http.authorizeHttpRequests().anyRequest().permitAll();
        return http.build();
    }
}
