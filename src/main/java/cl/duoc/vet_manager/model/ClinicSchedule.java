/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.model;

import cl.duoc.vet_manager.dto.response.TimeSlot;
import cl.duoc.vet_manager.model.VetWorkingSchedule.VetId;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicSchedule {
    private final Map<VetId, List<TimeSlot>> schedules = new HashMap<>();

    public List<TimeSlot> get(VetId vetId) {
        return schedules.getOrDefault(vetId, List.of());
    }

    public Map<VetId, List<TimeSlot>> asMap() {
        return Map.copyOf(schedules);
    }

    public List<VetId> vetIds() {
        return schedules.keySet().stream().toList();
    }

    public void add(VetId vetId, TimeSlot slot) {
        schedules.computeIfAbsent(vetId, _ -> new ArrayList<>()).add(slot);
    }

    public void set(VetId vetId, List<TimeSlot> slots) {
        schedules.put(vetId, slots);
    }

    public void removeTimeSlotRange(VetId vetId, LocalTime from, LocalTime until) {
        List<TimeSlot> slots = schedules.get(vetId);
        if (slots == null) {
            return;
        }
        slots.removeIf(
                slot -> slot.getStartTime().isBefore(until) && slot.getEndTime().isAfter(from));
    }
}
