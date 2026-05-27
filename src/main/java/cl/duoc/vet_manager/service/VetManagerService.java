/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.service;

import cl.duoc.vet_manager.client.AppointmentsClient;
import cl.duoc.vet_manager.client.VetsClient;
import cl.duoc.vet_manager.dto.appointments.response.AppointmentResponse;
import cl.duoc.vet_manager.dto.response.AvailabilityResponse;
import cl.duoc.vet_manager.dto.response.ScheduleAvailabilityReq;
import cl.duoc.vet_manager.dto.response.TimeSlot;
import cl.duoc.vet_manager.dto.vets.response.VeterinarioResponseDto;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class VetManagerService {
    private final VetsClient vetsClient;
    private final AppointmentsClient appointmentsClient;

    public Mono<List<AvailabilityResponse>> getAvailableScheduleHoursUseCase(ScheduleAvailabilityReq req) {
        int duration = (req.getSlotDurationMinutes() != null) ? req.getSlotDurationMinutes() : 15;

        return vetsClient.getAll().flatMap(vets -> {
            List<Long> vetIds = vets.stream().map(VeterinarioResponseDto::getId).toList();

            return appointmentsClient
                    .getBaselineSchedules(vetIds, req.getDate())
                    .map(appointments -> buildAvailabilityList(vets, appointments, req, duration));
        });
    }

    private List<AvailabilityResponse> buildAvailabilityList(
            List<VeterinarioResponseDto> allVets,
            List<AppointmentResponse> allAppointments,
            ScheduleAvailabilityReq req,
            int duration) {

        String targetDay = req.getDate().getDayOfWeek().name();

        return allVets.stream()
                .map(vet -> {
                    List<TimeSlot> freeSlots = calculateVetFreeSlots(vet, allAppointments, targetDay, req, duration);
                    return AvailabilityResponse.builder()
                            .professionalId(vet.getId())
                            .professionalName(vet.getNombreCompleto() + " " + vet.getApellidos())
                            .availableSlots(freeSlots)
                            .build();
                })
                .toList();
    }

    private List<TimeSlot> calculateVetFreeSlots(
            VeterinarioResponseDto vet,
            List<AppointmentResponse> allAppointments,
            String targetDay,
            ScheduleAvailabilityReq req,
            int duration) {

        return vet.getHorarioVeterinario().stream()
                .filter(shift -> shift.getDia().equalsIgnoreCase(targetDay))
                .findFirst()
                .map(shift -> {
                    List<AppointmentResponse> vetAppointments = allAppointments.stream()
                            .filter(app -> app.getProfessionalId().equals(vet.getId()))
                            .filter(app -> !"CANCELLED".equalsIgnoreCase(app.getStatus()))
                            .toList();

                    LocalTime effectiveStart = req.getStartTime().isAfter(shift.getHoraInicio())
                            ? req.getStartTime()
                            : shift.getHoraInicio();
                    LocalTime effectiveEnd =
                            req.getEndTime().isBefore(shift.getHoraFin()) ? req.getEndTime() : shift.getHoraFin();

                    return extractFreeBlocks(effectiveStart, effectiveEnd, vetAppointments, duration);
                })
                .orElse(List.of());
    }

    private List<TimeSlot> extractFreeBlocks(
            LocalTime start, LocalTime end, List<AppointmentResponse> booked, int durationMinutes) {
        List<TimeSlot> freeSlots = new ArrayList<>();

        if (start.isAfter(end) || start.equals(end)) {
            return freeSlots;
        }

        LocalTime currentSlotStart = start;

        while (!currentSlotStart.plusMinutes(durationMinutes).isAfter(end)) {
            LocalTime currentSlotEnd = currentSlotStart.plusMinutes(durationMinutes);

            final LocalTime checkStart = currentSlotStart;
            final LocalTime checkEnd = currentSlotEnd;

            boolean isBooked = booked.stream().anyMatch(app -> {
                LocalTime appStart = app.getScheduleAt().toLocalTime();
                LocalTime appEnd = app.getEndScheduleAt().toLocalTime();
                return checkStart.isBefore(appEnd) && checkEnd.isAfter(appStart);
            });

            if (!isBooked) {
                freeSlots.add(TimeSlot.builder()
                        .startTime(currentSlotStart)
                        .endTime(currentSlotEnd)
                        .build());
            }
            currentSlotStart = currentSlotEnd;
        }

        return freeSlots;
    }
}
