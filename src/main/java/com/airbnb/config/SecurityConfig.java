package com.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    // Configure Spring Security here.
    // For example, you can enable basic authentication and add custom login/logout endpoints.

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //h(cd)^2
        http.csrf().disable().cors().disable();
    //haas.configure
//        http.authorizeHttpRequests().anyRequest().permitAll();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/createUser","/api/v1/auth/createpropertyowner","/api/v1/auth/login")
                .permitAll()
                .requestMatchers("/api/v1/auth/property/addproperty").hasRole("OWNER")
                .requestMatchers("/api/v1/auth/createpropertyowner").hasRole("ADMIN")
                .anyRequest().authenticated();

        return http.build();
    }
}
