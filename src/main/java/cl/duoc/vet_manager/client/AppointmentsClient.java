/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.client;

import cl.duoc.vet_manager.dto.request.SearchAvailabilityRequest;
import cl.duoc.vet_manager.dto.response.SearchAvailabilityResponse;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface AppointmentsClient {
    String base = "/api/v1/appointments";

    @GetExchange("/api/v1/health")
    Map<String, String> getHealth();

    @PostExchange(base + "/schedules")
    List<SearchAvailabilityResponse> searchAvailability(@RequestBody SearchAvailabilityRequest req);
}
