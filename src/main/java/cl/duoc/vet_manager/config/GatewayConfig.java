/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.config;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfig {

    @Value("${app.service.appointments.base-url}")
    private String apptsBaseUrl;

    @Value("${app.service.pets.base-url}")
    private String petsBaseUrl;

    @Value("${app.service.vets.base-url}")
    private String vetsBaseUrl;

    @Bean
    public RouterFunction<ServerResponse> domainRoutes() {
        return route("appt_pt")
                .route(path("/api/v1/appointments/**"), http())
                .filter(uri(apptsBaseUrl))
                .build()
                .and(route("clinic_pt")
                        .route(path("/api/v1/clinical-records/**"), http())
                        .filter(uri(apptsBaseUrl))
                        .build())
                .and(route("pets_passthrough")
                        .route(path("/api/v1/pets/**"), http())
                        .filter(uri(petsBaseUrl))
                        .build())
                .and(route("vets_passthrough")
                        .route(path("/api/v1/vets/**"), http())
                        .filter(uri(vetsBaseUrl))
                        .build());
    }
}
