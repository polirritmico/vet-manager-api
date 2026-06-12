/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    public record ApptId(Long value) {
        public ApptId {
            java.util.Objects.requireNonNull(value);
        }
    }

    private ApptId id;
    private Long clientId;
    private Long petId;
    private Long professionalId;
    private LocalDateTime scheduleAt;
    private LocalDateTime endScheduleAt;
    // private String status;
    // private LocalDateTime createdAt;
    // private LocalDateTime updatedAt;
}
