package org.example.scheduleapp.User.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.User.dto.LoginRequest;
import org.example.scheduleapp.User.dto.LoginResponse;
import org.example.scheduleapp.User.entity.User;
import org.example.scheduleapp.User.repository.UserRepository;
import org.example.scheduleapp.common.PasswordEncoder;
import org.example.scheduleapp.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))  {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return LoginResponse.from(user);
    }
}
