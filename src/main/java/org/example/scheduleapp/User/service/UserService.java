package org.example.scheduleapp.User.service;


import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.User.dto.UserCreateRequest;
import org.example.scheduleapp.User.dto.UserResponse;
import org.example.scheduleapp.User.dto.UserUpdateRequest;
import org.example.scheduleapp.User.entity.User;
import org.example.scheduleapp.User.repository.UserRepository;
import org.example.scheduleapp.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword());

        User savedUser = userRepository.save(user);

        return UserResponse.from(savedUser);
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    public UserResponse getUser(Long userId) {
        User user = findUserById(userId);
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = findUserById(userId);

        user.update(request.getUsername(), request.getEmail());

        return UserResponse.from(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = findUserById(userId);

        userRepository.delete(user);
    }
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
