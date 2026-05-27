/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.controller;

import cl.duoc.vet_manager.dto.response.AvailabilityResponse;
import cl.duoc.vet_manager.dto.response.ScheduleAvailabilityReq;
import cl.duoc.vet_manager.service.VetManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/vet-manager")
@RequiredArgsConstructor
@Tag(
        name = "VetManager",
        description = "Provides advanced use cases through the orchestration of multiple microservices.")
public class VetManagerController {
    private VetManagerService service;

    @PostMapping("appointments/availability")
    @Operation(
            summary = "Show avaliable hours",
            description = "Check the professionals availability for the date range")
    public Mono<ResponseEntity<List<AvailabilityResponse>>> showAvaliableHours(
            @Valid @RequestBody ScheduleAvailabilityReq req) {

        return service.getAvailableScheduleHoursUseCase(req).map(ResponseEntity::ok);
    }
}
