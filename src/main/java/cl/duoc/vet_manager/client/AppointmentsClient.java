/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.client;

import java.util.Map;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface AppointmentsClient {
    public static final String base = "/api/v1/appointments";

    @GetExchange("/api/v1/health")
    Mono<Map<String, String>> getHealth();
}
