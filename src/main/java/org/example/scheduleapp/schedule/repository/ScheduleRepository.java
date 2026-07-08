package org.example.scheduleapp.schedule.repository;

import org.example.scheduleapp.schedule.entity.Schedule;
import org.example.scheduleapp.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findAllByUser(User user, Pageable pageable);
}
