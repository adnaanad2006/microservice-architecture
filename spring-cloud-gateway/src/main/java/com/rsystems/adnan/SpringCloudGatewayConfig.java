package com.rsystems.adnan;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class SpringCloudGatewayConfig {

    @Bean
    public RouteLocator gatewayRoute(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/client1/**")
                            .filters(f -> f.stripPrefix(1))
                            .uri("http://localhost:8001/")
                            .id("eurekaClient1"))
                .route(r -> r.path("/client2/**")
                            .filters(f -> f.stripPrefix(1))
                            .uri("http://localhost:8002/")
                            .id("eurekaClient2"))
                .route(r -> r.path("/client3/**")
                            .filters(f -> f.stripPrefix(1))
                            .uri("http://localhost:8003/")
                            .id("eurekaClient3"))
                .route(r -> r.path("/client7/**")
                            .filters(f -> f.stripPrefix(1))
                            .uri("http://localhost:8007/")
                            .id("eurekaClient7"))
                .build();
    }
}
