package org.example.scheduleapp.schedule.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.common.SessionConst;
import org.example.scheduleapp.exception.UnauthorizedException;
import org.example.scheduleapp.schedule.dto.ScheduleCreateRequest;
import org.example.scheduleapp.schedule.dto.ScheduleResponse;
import org.example.scheduleapp.schedule.dto.ScheduleUpdateRequest;
import org.example.scheduleapp.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.example.scheduleapp.common.SessionConst.LOGIN_USER_ID;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule (
            @Valid @RequestBody ScheduleCreateRequest request,
            HttpSession session
    ) {
        Long loginUserId = getLoginUserId(session);
        ScheduleResponse response = scheduleService.createSchedule(request, loginUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getSchedules() {
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedule(
            @PathVariable Long scheduleId,
            HttpSession session
    ) {
        Long loginUserId = getLoginUserId(session);

        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId, loginUserId));
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @Valid @RequestBody ScheduleUpdateRequest request,
            HttpSession session
    ) {
        Long loginUserId = getLoginUserId(session);
        ScheduleResponse response = scheduleService.updateSchedule(scheduleId, request, loginUserId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduledId,
            HttpSession session
    ) {
        Long loginUserId = getLoginUserId(session);
        scheduleService.deleteSchedule(scheduledId, loginUserId);
        return ResponseEntity.noContent().build();
    }

    private Long getLoginUserId(HttpSession session) {
        Long loginUserId =
                (Long) session.getAttribute(SessionConst.LOGIN_USER_ID);
        if (loginUserId == null) {
            throw new UnauthorizedException();
        }
        return loginUserId;

    }
}
