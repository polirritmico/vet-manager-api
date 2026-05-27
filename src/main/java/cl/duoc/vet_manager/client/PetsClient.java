/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.client;

import cl.duoc.vet_manager.dto.pets.PetsResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface PetsClient {
    public static final String base = "/api/v1/pets";

    @GetExchange(base + "/{id}")
    Mono<PetsResponse> getAppointment(@PathVariable("id") Integer id);

    @GetExchange("/api/v1/health")
    Mono<Map> getHealth();
}
