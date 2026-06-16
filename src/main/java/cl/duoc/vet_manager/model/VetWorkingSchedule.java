/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VetWorkingSchedule {
    public record VetId(@JsonValue Long value) {
        public VetId {
            java.util.Objects.requireNonNull(value);
        }
    }

    @JsonProperty("vetId")
    private VetId id;

    @JsonProperty("vetNombre")
    private String name;

    @JsonProperty("horaInicio")
    private LocalTime fromTime;

    @JsonProperty("horaFin")
    private LocalTime untilTime;
}
