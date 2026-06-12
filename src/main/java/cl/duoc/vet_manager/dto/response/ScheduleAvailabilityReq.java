/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class ScheduleAvailabilityReq {
    private Long customerId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer slotDurationMinutes;
}
