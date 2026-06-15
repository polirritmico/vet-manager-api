/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.client;

import cl.duoc.vet_manager.dto.appointments.response.AppointmentResponse;
import cl.duoc.vet_manager.model.VetWorkingSchedule.VetId;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface AppointmentsClient {
    String base = "/api/v1/appointments";

    @GetExchange("/api/v1/health")
    Map<String, String> getHealth();

    default List<AppointmentResponse> getScheduledAppointments(List<VetId> professionalIds, LocalDate date) {
        List<Long> ids = professionalIds == null
                ? List.of()
                : professionalIds.stream().map(VetId::value).toList();
        return getSchedulesForProfessionals(ids, date);
    }

    @GetExchange(base + "/schedules")
    List<AppointmentResponse> getSchedulesForProfessionals(
            @RequestParam("professionalIds") List<Long> professionalIds,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
