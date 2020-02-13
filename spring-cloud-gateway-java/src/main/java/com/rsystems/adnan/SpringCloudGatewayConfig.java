package com.rsystems.adnan;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayConfig {

    @Bean
    public RouteLocator gatewayRoute(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/client1/**")
                            .filters(f -> f.stripPrefix(1)
                                    .addRequestHeader("new-header-req-1", "first-request-header")
                                    .addResponseHeader("new-header-res-1", "first-response-header")
                                    .hystrix(config -> config.setName("Eureka Client 1")
                                                        .setFallbackUri("forward:/client1Fallback")
                                    )
                            )
                            //.uri("http://localhost:8001/")
                            .uri("lb://EUREKA-CLIENT-1")
                            .id("eurekaClient1"))
                .route(r -> r.path("/client2/**")
                            .filters(f -> f.stripPrefix(1)
                                    .hystrix(config -> config.setName("Eureka Client 2")
                                            .setFallbackUri("forward:/client2Fallback")
                                    ))
                            //.uri("http://localhost:8002/")
                            .uri("lb://EUREKA-CLIENT-2")
                            .id("eurekaClient2"))
                .route(r -> r.path("/client3/**")
                            .filters(f -> f.stripPrefix(1)
                                    .hystrix(config -> config.setName("Eureka Client 3")
                                            .setFallbackUri("forward:/client3Fallback")
                                    ))
                            //.uri("http://localhost:8003/")
                            .uri("lb://EUREKA-CLIENT-3")
                            .id("eurekaClient3"))
                .route(r -> r.path("/client7/**")
                            .filters(f -> f.stripPrefix(1)
                                    .hystrix(config -> config.setName("Eureka Client 7")
                                            .setFallbackUri("forward:/client7Fallback")
                                    ))
                            //.uri("http://localhost:8007/")
                            .uri("lb://EUREKA-CLIENT-7")
                            .id("eurekaClient7"))
                .build();
    }
}
