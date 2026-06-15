/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.controller;

import cl.duoc.vet_manager.client.AppointmentsClient;
import cl.duoc.vet_manager.client.VetsClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name = "System", description = "Service monitoring & status")
public class StatusController {
    private final AppointmentsClient appointmentsClient;
    private final VetsClient vetsClient;

    @GetMapping("/health")
    @Operation(summary = "Check service health", description = "Checks the microservice avaliability")
    public ResponseEntity<Map<String, String>> checkHealth() {
        return ResponseEntity.ok(Map.of("status", "OK", "service", "VetManager"));
    }

    @GetMapping("/health/appointments")
    @Operation(
            summary = "Check the appointments-api health",
            description = "Checks the Appointments API microservice avaliability")
    public Map<String, String> checkAppointmentsHealth() {
        log.info("[checkAppointmentsHealth] Received request");
        return appointmentsClient.getHealth();
    }

    @GetMapping("/health/vets")
    @Operation(summary = "Check the vets-api health", description = "Checks the Vets API microservice avaliability")
    public Map<String, String> checkVetHealth() {
        log.info("[checkAppointmentsHealth] Received request");
        return vetsClient.getHealth();
    }
}
