package org.example.scheduleapp.User.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.User.dto.LoginRequest;
import org.example.scheduleapp.User.dto.LoginResponse;
import org.example.scheduleapp.User.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.example.scheduleapp.common.SessionConst.LOGIN_USER_ID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session
    ) {
        LoginResponse response = authService.login(request);
        
        session.setAttribute(LOGIN_USER_ID, response.getUserId()); // 세션 생성

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        return ResponseEntity.noContent().build();
    }
}
