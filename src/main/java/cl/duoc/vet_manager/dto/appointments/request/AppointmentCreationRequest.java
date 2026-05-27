/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreationRequest {
    @NotNull(message = "La consulta debe estar asociada a un cliente")
    @Positive(message = "La id del cliente no puede ser negativa")
    private Long clientId;

    @NotNull(message = "La consulta debe estar asociada a un mascota")
    @Positive(message = "La id de la mascota no puede ser negativa")
    private Long petId;

    @NotNull(message = "La consulta debe estar asociada a un profesional")
    @Positive(message = "El identificador del profesional no puede ser negativo")
    private Long professionalId;

    @NotNull(message = "La consulta debe tener fecha y hora")
    @FutureOrPresent
    private LocalDateTime scheduleAt;

    @NotNull(message = "La consulta debe tener fecha y hora de término")
    @FutureOrPresent
    private LocalDateTime endScheduleAt;
}
