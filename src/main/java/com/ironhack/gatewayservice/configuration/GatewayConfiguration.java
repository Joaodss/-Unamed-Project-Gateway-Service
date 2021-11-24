package com.ironhack.gatewayservice.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path(""/* + original path here */)
                        .uri("lb://"/* + service name here */))
                .route(p -> p.path(""/* + original path here */)
                        .uri("lb://"/* + service name here */))
                .build();
    }

}
