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
import cl.duoc.vet_manager.dto.response.TimeSlot;
import cl.duoc.vet_manager.mapper.DtoModelMapper;
import cl.duoc.vet_manager.model.Appointment;
import cl.duoc.vet_manager.model.StoreSchedule;
import cl.duoc.vet_manager.model.VetWorkingSchedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public List<AvailabilityResponse> getAvailableScheduleHoursUseCase(ScheduleAvailabilityReq req) {
        int slot = req.getSlotDurationMinutes();
        LocalTime from = roundUpToSlot(req.getStartTime(), slot);
        LocalTime until = roundUpToSlot(req.getEndTime(), slot);
        LocalDate date = req.getDate();

        StoreSchedule schedule = buildStoreSchedule(vetsClient.getDayWorkingSchedules(date), slot);
        List<Appointment> appts = mapper.toAppointments(apptsClient.getScheduledAppointments(schedule.vetIds(), date));

        return mapper.toAvailabilityResponse(buildAvailabilityList(from, until, slot, appts, schedule));
    }

    private StoreSchedule buildStoreSchedule(List<VetWorkingSchedule> vetSchedules, int slotDuration) {
        StoreSchedule schedule = new StoreSchedule();
        for (VetWorkingSchedule vetSchedule : vetSchedules) {
            schedule.set(
                    vetSchedule.getId(),
                    generateTimeSlotsRange(vetSchedule.getFromTime(), vetSchedule.getUntilTime(), slotDuration));
        }
        return schedule;
    }

    private List<TimeSlot> generateTimeSlotsRange(LocalTime from, LocalTime until, int slotMinutes) {
        List<TimeSlot> slots = new ArrayList<>();
        for (LocalTime start = from, end = start.plusMinutes(slotMinutes);
                !end.isAfter(until);
                start = end, end = end.plusMinutes(slotMinutes)) {
            slots.add(new TimeSlot(start, end));
        }
        return slots;
    }

    private LocalTime roundUpToSlot(LocalTime time, int slotDurationMinutes) {
        int minutes = time.getMinute();
        int remainder = minutes % slotDurationMinutes;
        if (remainder == 0) {
            return time.withSecond(0).withNano(0);
        }
        return time.plusMinutes(slotDurationMinutes - remainder).withSecond(0).withNano(0);
    }

    private StoreSchedule buildAvailabilityList(
            LocalTime startTime,
            LocalTime endTime,
            int slotDuration,
            List<Appointment> currentAppts,
            StoreSchedule schedule) {

        for (LocalTime from = startTime; from.isBefore(endTime); from = from.plusMinutes(slotDuration)) {
            LocalTime until = from.plusMinutes(slotDuration);
            if (until.isAfter(endTime)) {
                break;
            }

            List<Appointment> appts = getAppointmentsAtTime(startTime, endTime, currentAppts);

            // for (Appointment appt : currentAppts) {
            //     if (!isAvailable(from, until, appt)) {
            //         continue;
            //     }
            //
            //     TimeSlot slot = new TimeSlot(from, until);
            //     vetSchedules
            //             .computeIfAbsent(appt.getProfessionalId(), k -> new ArrayList<>())
            //             .add(slot);
            // }
        }

        return mapper.toAvailabilityResponse(vetSchedules);
    }

    // private boolean isAvailable(LocalTime from, LocalTime until, Appointment appt) {
    //     return from.isBefore(appt.getEndScheduleAt()) && until.isAfter(appt.getScheduleAt());
    // }
}
