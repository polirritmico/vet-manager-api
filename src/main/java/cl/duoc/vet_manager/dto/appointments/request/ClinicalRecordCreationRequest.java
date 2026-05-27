/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalRecordCreationRequest {
    @NotNull(message = "El id de la consulta es obligatorio")
    @Positive(message = "El id de la consulta no puede ser negativo")
    private Long appoinmentId;

    private String visitReason;

    private String diagnosis;

    private String treatment;

    private String notes;
}
