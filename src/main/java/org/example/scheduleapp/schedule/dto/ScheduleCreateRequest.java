package org.example.scheduleapp.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleCreateRequest {

    private String username;
    private String title;
    private String content;
}
