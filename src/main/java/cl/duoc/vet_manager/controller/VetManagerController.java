/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.controller;

import cl.duoc.vet_manager.api.VetManagerApi;
import cl.duoc.vet_manager.dto.request.ScheduleAvailabilityRequest;
import cl.duoc.vet_manager.dto.response.SearchAvailabilityResponse;
import cl.duoc.vet_manager.service.VetManagerService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vet-manager")
@RequiredArgsConstructor
public class VetManagerController implements VetManagerApi {
    private final VetManagerService service;

    @PostMapping("appointments/availability")
    public ResponseEntity<List<SearchAvailabilityResponse>> showAvaliableHours(
            @Valid @RequestBody ScheduleAvailabilityRequest req) {

        return ResponseEntity.ok(service.getAvailableScheduleHoursUseCase(req));
    }
}
