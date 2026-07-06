package org.example.scheduleapp.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.schedule.dto.ScheduleCreateRequest;
import org.example.scheduleapp.schedule.dto.ScheduleResponse;
import org.example.scheduleapp.schedule.dto.ScheduleUpdateRequest;
import org.example.scheduleapp.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule (
            @Valid @RequestBody ScheduleCreateRequest request
    ) {
        ScheduleResponse response = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getSchedules() {
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedule(
            @PathVariable Long scheduleId
    ) {
        ScheduleResponse response = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @Valid @RequestBody ScheduleUpdateRequest request
    ) {
        ScheduleResponse response = scheduleService.updateSchedule(scheduleId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduledId
    ) {
        scheduleService.deleteSchedule(scheduledId);
        return ResponseEntity.noContent().build();
    }
}
