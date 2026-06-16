/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.mapper;

import cl.duoc.vet_manager.dto.request.ScheduleAvailabilityRequest;
import cl.duoc.vet_manager.dto.request.SearchAvailabilityRequest;
import cl.duoc.vet_manager.dto.response.AvailabilityResponse;
import cl.duoc.vet_manager.dto.response.SearchAvailabilityResponse;
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

    public List<AvailabilityResponse> toAvailabilityResponse(List<SearchAvailabilityResponse> storeSchedule) {
        return storeSchedule.stream()
        .map().toList();

        // return storeSchedule.asMap().entrySet().stream()
        //         .map(vetSchedule -> AvailabilityResponse.builder()
        //                 .professionalId(vetSchedule.getKey().value())
        //                 .availableSlots(vetSchedule.getValue())
        //                 .build())
        //         .toList();
    }

    // public ClinicSchedule toClinicSchedule(List<VetWorkingSchedule> schedules) {
    //     ClinicSchedule storeSchedule = new ClinicSchedule();
    //     for (VetWorkingSchedule schedule : schedules) {
    //         storeSchedule.add(schedule.getId(), new TimeSlot(schedule.getFromTime(), schedule.getUntilTime()));
    //     }
    //     return storeSchedule;
    // }
    //
    // public List<AvailabilityResponse> toAvailabilityResponse(ClinicSchedule storeSchedule) {
    //     return storeSchedule.asMap().entrySet().stream()
    //             .map(vetSchedule -> AvailabilityResponse.builder()
    //                     .professionalId(vetSchedule.getKey().value())
    //                     .availableSlots(vetSchedule.getValue())
    //                     .build())
    //             .toList();
    // }
    //
    // public List<Appointment> toAppointments(List<AppointmentResponse> rawAppointments) {
    //     return rawAppointments.stream()
    //             .map(appt -> Appointment.builder()
    //                     .id(new ApptId(appt.getId()))
    //                     .professionalId(new VetId(appt.getProfessionalId()))
    //                     .clientId(appt.getClientId())
    //                     .petId(appt.getPetId())
    //                     .scheduleAt(appt.getScheduleAt().toLocalTime())
    //                     .endScheduleAt(appt.getEndScheduleAt().toLocalTime())
    //                     .build())
    //             .toList();
    // }
}
