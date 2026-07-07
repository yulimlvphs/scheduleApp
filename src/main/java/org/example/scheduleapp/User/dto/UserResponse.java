package org.example.scheduleapp.User.dto;
import lombok.Getter;
import org.example.scheduleapp.User.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserResponse {

    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 이건 무슨 역할을 한다기 보다는 다른 곳에서 함부로 생성자를 사용할 수 없도록 조치를 취해둔 것.
    private UserResponse(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // Entity -> DTO 변환기 (Mapper)
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
