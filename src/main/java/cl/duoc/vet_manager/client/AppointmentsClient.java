/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.client;

import cl.duoc.vet_manager.dto.appointments.response.AppointmentResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface AppointmentsClient {
    public static final String base = "/api/v1/appointments";

    @GetExchange("/api/v1/health")
    Map<String, String> getHealth();

    @GetExchange(base + "/schedules")
    List<AppointmentResponse> getBaselineSchedules(
            @RequestParam("professionalIds") List<Long> professionalIds, @RequestParam("date") LocalDate date);
}
