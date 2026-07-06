package org.example.scheduleapp.exception;

public class ScheduleNotFoundException extends RuntimeException{

    public ScheduleNotFoundException(Long scheduleId) {
        super("존재하지 않은 일정입니다. scheduleId = " + scheduleId);
    }
}
