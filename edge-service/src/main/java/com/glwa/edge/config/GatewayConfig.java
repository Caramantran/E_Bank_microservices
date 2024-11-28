package com.glwa.edge.config;

import com.glwa.edge.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    private final JwtAuthenticationFilter filter;

    public GatewayConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/user/**")
                        .uri("lb://user-service"))

                .route("customer-service", r -> r.path("/customer/**")
                        .uri("lb://customer-service"))

                .route("account-service", r -> r.path("/account/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://account-service"))

                .route("auth-service", r -> r.path("/auth/**")
                        .uri("lb://auth-service"))

                .route("transaction-service", r -> r.path("/transaction/**")
                        .uri("lb://transaction-service"))
                .build();
    }
}