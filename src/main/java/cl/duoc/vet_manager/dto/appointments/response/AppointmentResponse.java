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
public class AppointmentResponse {
    private Long id;
    private Long clientId;
    private Long petId;
    private Long professionalId;
    private LocalDateTime scheduleAt;
    private LocalDateTime endScheduleAt;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
