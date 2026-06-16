/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.model;

import cl.duoc.vet_manager.model.VetWorkingSchedule.VetId;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    public record ApptId(@JsonValue Long value) {
        public ApptId {
            java.util.Objects.requireNonNull(value);
        }
    }

    private ApptId id;
    private VetId professionalId;
    private Long clientId;
    private Long petId;
    private LocalTime scheduleAt;
    private LocalTime endScheduleAt;
}
