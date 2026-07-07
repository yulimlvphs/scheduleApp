package org.example.scheduleapp.schedule.dto;
import lombok.Getter;
import org.example.scheduleapp.schedule.entity.Schedule;
import java.time.LocalDateTime;

@Getter
public class ScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private ScheduleResponse(
            Long id,
            String title,
            String content,
            String username,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getUsername(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}