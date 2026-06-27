/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.config;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Slf4j
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
                .filter(getRoutingFilter(apptsBaseUrl))
                .build()
                .and(route("clinic_pt")
                        .route(path("/api/v1/clinical-records/**"), http())
                        .filter(getRoutingFilter(apptsBaseUrl))
                        .build())
                .and(route("pets_passthrough")
                        .route(path("/api/v1/pets/**"), http())
                        .filter(getRoutingFilter(petsBaseUrl))
                        .build())
                .and(route("vets_passthrough")
                        .route(path("/api/v1/vets/**"), http())
                        .filter(getRoutingFilter(vetsBaseUrl))
                        .build())
                .and(route("appt_docs")
                        .route(path("/appointments/v3/api-docs"), http())
                        .before(rewritePath("/appointments/(?<segment>.*)", "/${segment}"))
                        .filter(getRoutingFilter(apptsBaseUrl))
                        .build())
                .and(route("pets_docs")
                        .route(path("/pets/v3/api-docs"), http())
                        .before(rewritePath("/pets/(?<segment>.*)", "/${segment}"))
                        .filter(getRoutingFilter(petsBaseUrl))
                        .build())
                .and(route("vets_docs")
                        .route(path("/vets/v3/api-docs"), http())
                        .before(rewritePath("/vets/(?<segment>.*)", "/${segment}"))
                        .filter(getRoutingFilter(vetsBaseUrl))
                        .build());
    }

    private HandlerFilterFunction<ServerResponse, ServerResponse> getRoutingFilter(String url) {
        log.info("Routing URL: '{}'", url);
        return isLocalURL(url) ? uri(url) : lb(URI.create(url).getHost());
    }

    private boolean isLocalURL(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Missing domain service url");
        }
        try {
            String host = URI.create(url).getHost();
            return host != null
                    && (!url.startsWith("lb://"))
                    && (host.equalsIgnoreCase("localhost")
                            || host.equals("127.0.0.1")
                            || host.equals("[::1]")
                            || host.equals("0:0:0:0:0:0:0:1"));
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Bad domain service url: " + url);
        }
    }
}
