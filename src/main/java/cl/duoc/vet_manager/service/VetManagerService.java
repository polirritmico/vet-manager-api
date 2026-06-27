/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.service;

import cl.duoc.vet_manager.client.AppointmentsClient;
import cl.duoc.vet_manager.client.VetsClient;
import cl.duoc.vet_manager.dto.request.ScheduleAvailabilityRequest;
import cl.duoc.vet_manager.dto.request.SearchAvailabilityRequest;
import cl.duoc.vet_manager.dto.response.SearchAvailabilityResponse;
import cl.duoc.vet_manager.mapper.DtoModelMapper;
import cl.duoc.vet_manager.model.VetWorkingSchedule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VetManagerService {
    private final VetsClient vetsClient;
    private final AppointmentsClient apptsClient;
    private final DtoModelMapper mapper;

    public List<SearchAvailabilityResponse> getAvailableScheduleHoursUseCase(ScheduleAvailabilityRequest req) {
        log.info("Starting getAvailableScheduleHoursUseCase for date: {}", req.getDate());
        long startTime = System.currentTimeMillis();

        List<VetWorkingSchedule> vetSchedules = vetsClient.getDayWorkingSchedules(req.getDate());
        SearchAvailabilityRequest searchAvailabilityRequest = mapper.toSearchAvailabilityRequest(req, vetSchedules);
        List<SearchAvailabilityResponse> response = apptsClient.searchAvailability(searchAvailabilityRequest);

        String message = "Completed getAvailableScheduleHoursUseCase in {} ms. Found {} available slots.";
        long executionTime = System.currentTimeMillis() - startTime;
        log.info(message, executionTime, response.size());

        return response;
    }
}
