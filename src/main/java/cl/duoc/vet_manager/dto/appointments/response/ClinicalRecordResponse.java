/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalRecordResponse {
    private Long id;
    private Long appointmentId;
    private Long clientId;
    private Long petId;
    private Long professionalId;
    private String visitReason;
    private String diagnosis;
    private String treatment;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
