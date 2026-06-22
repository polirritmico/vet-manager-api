/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.client;

import cl.duoc.vet_manager.dto.response.vets.VeterinarioResponseDTO;
import cl.duoc.vet_manager.model.VetWorkingSchedule;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface VetsClient {
    String base = "/api/v1/vets";

    @GetExchange("/api/v1/health")
    Map<String, String> getHealth();

    @GetExchange(base)
    List<VeterinarioResponseDTO> getAll();

    @GetExchange(base + "/schedules")
    List<VetWorkingSchedule> getDayWorkingSchedules(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
