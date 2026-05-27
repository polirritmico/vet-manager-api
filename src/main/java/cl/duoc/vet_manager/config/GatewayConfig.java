/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Value("${app.service.appointments.base-url}")
    private String apptsBaseUrl;

    @Value("${app.service.pets.base-url}")
    private String petsBaseUrl;

    @Value("${app.service.vets.base-url}")
    private String vetsBaseUrl;

    @Bean
    public RouteLocator domainRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("appt_pt", req -> req.path("/api/v1/appointments/**").uri(apptsBaseUrl))
                .route("clinic_pt", r -> r.path("/api/v1/clinical-records/**").uri(apptsBaseUrl))
                .route("pets_passthrough", req -> req.path("/api/v1/pets/**").uri(petsBaseUrl))
                .route("vets_passthrough", req -> req.path("/api/v1/vets/**").uri(vetsBaseUrl))
                .build();
    }
}
