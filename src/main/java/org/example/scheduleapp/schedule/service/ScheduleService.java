package org.example.scheduleapp.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.example.scheduleapp.exception.ScheduleNotFoundException;
import org.example.scheduleapp.exception.UserNotFoundException;
import org.example.scheduleapp.schedule.dto.ScheduleCreateRequest;
import org.example.scheduleapp.schedule.dto.ScheduleResponse;
import org.example.scheduleapp.schedule.dto.ScheduleUpdateRequest;
import org.example.scheduleapp.schedule.entity.Schedule;
import org.example.scheduleapp.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponse createSchedule(ScheduleCreateRequest request, Long loginUserId) {
        User user = findUserById(loginUserId);

        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                user
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponse.from(savedSchedule);
    }

    public List<ScheduleResponse> getSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponse::from)
                .toList();
    }

    public ScheduleResponse getSchedule(Long scheduleId) {

        Schedule schedule = findScheduleById(scheduleId);

        return ScheduleResponse.from(schedule);
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = findScheduleById(scheduleId);

        schedule.update(
                request.getTitle(),
                request.getContent()
        );

        return ScheduleResponse.from(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);

        scheduleRepository.delete(schedule);
    }

    private Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException(scheduleId));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
