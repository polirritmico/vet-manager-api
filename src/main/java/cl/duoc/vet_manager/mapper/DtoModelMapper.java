/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.mapper;

import cl.duoc.vet_manager.dto.request.ScheduleAvailabilityRequest;
import cl.duoc.vet_manager.dto.request.SearchAvailabilityRequest;
import cl.duoc.vet_manager.model.VetWorkingSchedule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoModelMapper {

    public SearchAvailabilityRequest toSearchAvailabilityRequest(
            ScheduleAvailabilityRequest req, List<VetWorkingSchedule> vetSchedules) {
        return SearchAvailabilityRequest.builder()
                .date(req.getDate())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .slotDurationMinutes(req.getSlotDurationMinutes())
                .vetSchedules(vetSchedules)
                .build();
    }
}
