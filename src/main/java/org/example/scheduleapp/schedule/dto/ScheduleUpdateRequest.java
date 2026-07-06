package org.example.scheduleapp.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleUpdateRequest {

    private String title;

    private String content;
}
