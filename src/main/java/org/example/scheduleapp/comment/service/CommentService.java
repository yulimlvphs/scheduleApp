package org.example.scheduleapp.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.comment.dto.CommentCreateRequest;
import org.example.scheduleapp.comment.dto.CommentResponse;
import org.example.scheduleapp.comment.entity.Comment;
import org.example.scheduleapp.comment.repository.CommentRespository;
import org.example.scheduleapp.exception.ScheduleNotFoundException;
import org.example.scheduleapp.exception.UserNotFoundException;
import org.example.scheduleapp.schedule.entity.Schedule;
import org.example.scheduleapp.schedule.repository.ScheduleRepository;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRespository commentRespository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponse createComment(
            Long scheduleId,
            Long loginUserId,
            CommentCreateRequest request
    ) {
        User user = findUserById(loginUserId);
        Schedule schedule = findScheduleById(scheduleId);

        Comment commet = new Comment(request.getContent(), user, schedule);

        Comment savedComment = commentRespository.save(commet);

        return CommentResponse.from(savedComment);
    }

    public List<CommentResponse> getComments(Long scheduleId) {
        return commentRespository.findAllByScheduleId(scheduleId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException(scheduleId));
    }
}
