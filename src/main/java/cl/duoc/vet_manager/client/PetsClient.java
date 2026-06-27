/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.client;

import cl.duoc.vet_manager.dto.response.pets.MascotaResponseDTO;
import java.util.List;
import java.util.Map;
import org.springframework.web.service.annotation.GetExchange;

public interface PetsClient {
    public static final String base = "/api/v1/pets";

    @GetExchange("/api/v1/health")
    Map<String, String> getHealth();

    @GetExchange(base)
    List<MascotaResponseDTO> getAll();
}
