/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ScheduleAvailabilityRequest", description = "Payload to search schedule availability")
public class ScheduleAvailabilityRequest {
    @NotNull
    @Schema(description = "Customer identifier", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Long customerId;

    @NotNull(message = "La fecha es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Search date (yyyy-MM-dd)", example = "2030-06-18", requiredMode = RequiredMode.REQUIRED)
    private LocalDate date;

    @NotNull(message = "El tiempo de inicio es obligatorio")
    @JsonFormat(pattern = "HH:mm")
    @Schema(description = "Start time (HH:mm)", example = "08:00", requiredMode = RequiredMode.REQUIRED)
    private LocalTime startTime;

    @NotNull(message = "El tiempo de término es obligatorio")
    @JsonFormat(pattern = "HH:mm")
    @Schema(description = "End time (HH:mm)", example = "21:00", requiredMode = RequiredMode.REQUIRED)
    private LocalTime endTime;

    @NotNull(message = "La ranura en minutos de la consulta es obligatoria")
    @Schema(description = "Schedule slot duration in minutes", example = "15", requiredMode = RequiredMode.REQUIRED)
    private Integer slotDurationMinutes;
}
