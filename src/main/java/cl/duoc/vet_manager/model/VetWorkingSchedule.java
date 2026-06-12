/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.model;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VetWorkingSchedule {
    public record VetId(Long value) {
        public VetId {
            java.util.Objects.requireNonNull(value);
        }
    }

    private VetId id;
    private LocalTime fromTime;
    private LocalTime untilTime;
}
