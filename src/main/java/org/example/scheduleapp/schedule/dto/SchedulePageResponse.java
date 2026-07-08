package org.example.scheduleapp.schedule.dto;
import lombok.Getter;
import org.example.scheduleapp.schedule.entity.Schedule;
import java.time.LocalDateTime;

@Getter
public class SchedulePageResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final int commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;

    private SchedulePageResponse(
            Long id,
            String title,
            String content,
            int commentCount,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            String username
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
    }

    public static SchedulePageResponse of(
            Schedule schedule,
            int commentCount
    ) {
        return new SchedulePageResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                commentCount,
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                schedule.getUser().getUsername()
        );
    }
}
