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
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
@Configuration
public class RestClientConfig {

    @Value("${app.service.appointments.base-url}")
    private String appointmentsBaseUrl;

    @Value("${app.service.pets.base-url}")
    private String petsBaseUrl;

    @Value("${app.service.vets.base-url}")
    private String vetsBaseUrl;

    @Bean
    public AppointmentsClient appointmentsClient() {
        RestClient client = RestClient.builder()
                .baseUrl(appointmentsBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .requestInterceptor(tokenPropagationInterceptor())
                .requestInterceptor((request, body, execution) -> {
                    log.debug("Request: {} {} | Body: {}", request.getMethod(), request.getURI(), new String(body));
                    return execution.execute(request, body);
                })
                .build();

        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client))
                .build()
                .createClient(AppointmentsClient.class);
    }

    @Bean
    public PetsClient petsClient() {
        RestClient client = RestClient.builder()
                .baseUrl(petsBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .requestInterceptor(tokenPropagationInterceptor())
                .build();

        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client))
                .build()
                .createClient(PetsClient.class);
    }

    @Bean
    public VetsClient vetsClient() {
        RestClient client = RestClient.builder()
                .baseUrl(vetsBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .requestInterceptor(tokenPropagationInterceptor())
                .build();

        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client))
                .build()
                .createClient(VetsClient.class);
    }

    private ClientHttpRequestInterceptor tokenPropagationInterceptor() {
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attrs != null) {
                    HttpServletRequest servletRequest = attrs.getRequest();
                    String authHeader = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        request.getHeaders().add(HttpHeaders.AUTHORIZATION, authHeader);
                    }
                }
                return execution.execute(request, body);
            }
        };
    }
}
