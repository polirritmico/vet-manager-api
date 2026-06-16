/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.mapper;

import cl.duoc.vet_manager.dto.appointments.response.AppointmentResponse;
import cl.duoc.vet_manager.dto.response.AvailabilityResponse;
import cl.duoc.vet_manager.dto.response.TimeSlot;
import cl.duoc.vet_manager.model.Appointment;
import cl.duoc.vet_manager.model.Appointment.ApptId;
import cl.duoc.vet_manager.model.ClinicSchedule;
import cl.duoc.vet_manager.model.VetWorkingSchedule;
import cl.duoc.vet_manager.model.VetWorkingSchedule.VetId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoModelMapper {

    public ClinicSchedule toClinicSchedule(List<VetWorkingSchedule> schedules) {
        ClinicSchedule storeSchedule = new ClinicSchedule();
        for (VetWorkingSchedule schedule : schedules) {
            storeSchedule.add(schedule.getId(), new TimeSlot(schedule.getFromTime(), schedule.getUntilTime()));
        }
        return storeSchedule;
    }

    public List<AvailabilityResponse> toAvailabilityResponse(ClinicSchedule storeSchedule) {
        return storeSchedule.asMap().entrySet().stream()
                .map(vetSchedule -> AvailabilityResponse.builder()
                        .professionalId(vetSchedule.getKey().value())
                        .availableSlots(vetSchedule.getValue())
                        .build())
                .toList();
    }

    public List<Appointment> toAppointments(List<AppointmentResponse> rawAppointments) {
        return rawAppointments.stream()
                .map(appt -> Appointment.builder()
                        .id(new ApptId(appt.getId()))
                        .professionalId(new VetId(appt.getProfessionalId()))
                        .clientId(appt.getClientId())
                        .petId(appt.getPetId())
                        .scheduleAt(appt.getScheduleAt().toLocalTime())
                        .endScheduleAt(appt.getEndScheduleAt().toLocalTime())
                        .build())
                .toList();
    }
}
