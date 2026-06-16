/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.dto.request;

import cl.duoc.vet_manager.model.VetWorkingSchedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchAvailabilityRequest {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int slotDurationMinutes;

    private List<VetWorkingSchedule> vetSchedules;
}
