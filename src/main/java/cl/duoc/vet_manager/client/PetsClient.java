/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.client;

import cl.duoc.vet_manager.dto.pets.response.MascotaResponseDTO;
import java.util.List;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface PetsClient {
    public static final String base = "/api/v1/pets";

    @GetExchange(base)
    Mono<List<MascotaResponseDTO>> getAll();
}
