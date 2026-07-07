package org.example.scheduleapp.user.dto;
import lombok.Getter;
import org.example.scheduleapp.user.entity.User;

@Getter
public class LoginResponse {

    private final Long userId;
    private final String username;
    private final String email;

    private LoginResponse(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public static LoginResponse from(User user) {
        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}