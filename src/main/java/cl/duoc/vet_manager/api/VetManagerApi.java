/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.api;

import cl.duoc.vet_manager.dto.request.ScheduleAvailabilityReq;
import cl.duoc.vet_manager.dto.response.AvailabilityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "VetManager",
        description = "Provides advanced use cases through the orchestration of multiple microservices.")
public interface VetManagerApi {
    @Operation(
            summary = "Get available schedule time slots",
            description =
                    "Send requests to Vets, Appointments and Pets microservices and returns a list of available time slots for schedules.")
    @ApiResponse(
            responseCode = "200",
            description = "Return a list of available appointment slots.",
            content = @Content)
    @ApiResponse(responseCode = "403", description = "Invalid credentials or account revoked", content = @Content)
    public ResponseEntity<List<AvailabilityResponse>> showAvaliableHours(ScheduleAvailabilityReq req);
}
