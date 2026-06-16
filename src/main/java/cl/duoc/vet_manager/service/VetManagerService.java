/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.service;

import cl.duoc.vet_manager.client.AppointmentsClient;
import cl.duoc.vet_manager.client.VetsClient;
import cl.duoc.vet_manager.dto.request.ScheduleAvailabilityReq;
import cl.duoc.vet_manager.dto.response.AvailabilityResponse;
import cl.duoc.vet_manager.mapper.DtoModelMapper;
import cl.duoc.vet_manager.model.ClinicSchedule;
import cl.duoc.vet_manager.model.VetWorkingSchedule;
import java.time.LocalDate;
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

    public List<AvailabilityResponse> getAvailableScheduleHoursUseCaseB(ScheduleAvailabilityReq req) {
        LocalDate date = req.getDate();

        List<VetWorkingSchedule> vetSchedules = vetsClient.getDayWorkingSchedules(date);
        SlotsAvailabilityRequest slotsAvailabilityRequest = mapper.toSlotsAvailabilityRequest(req, vetSchedules);
        List<ClinicSchedule> availabilitySchedule = apptsClient.searchAvailability(slotsAvailabilityRequest);

        return mapper.toAvailabilityResponse(availabilitySchedule);
    }
}
