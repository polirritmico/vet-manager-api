/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.config;

import cl.duoc.vet_manager.client.AppointmentsClient;
import cl.duoc.vet_manager.client.PetsClient;
import cl.duoc.vet_manager.client.VetsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Value("${app.service.appointments.base-url}")
    private String appointmentsBaseUrl;

    @Value("${app.service.pets.base-url}")
    private String petsBaseUrl;

    @Value("${app.service.vets.base-url}")
    private String vetsBaseUrl;

    @Bean
    public AppointmentsClient appointmentsClient() {
        WebClient client = WebClient.builder()
                .baseUrl(appointmentsBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filter(tokenPropagationFilter())
                .build();

        return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client))
                .build()
                .createClient(AppointmentsClient.class);
    }

    @Bean
    public PetsClient petsClient() {
        WebClient client = WebClient.builder()
                .baseUrl(petsBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filter(tokenPropagationFilter())
                .build();

        return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client))
                .build()
                .createClient(PetsClient.class);
    }

    @Bean
    public VetsClient vetsClient() {
        WebClient client = WebClient.builder()
                .baseUrl(vetsBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filter(tokenPropagationFilter())
                .build();

        return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client))
                .build()
                .createClient(VetsClient.class);
    }

    private ExchangeFilterFunction tokenPropagationFilter() {
        return (req, next) -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getCredentials() instanceof String token) {
                ClientRequest newReq = ClientRequest.from(req)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .build();
                return next.exchange(newReq);
            }
            return next.exchange(req);
        };
    }
}
