package org.example.scheduleapp.comment.controller;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.comment.dto.CommentCreateRequest;
import org.example.scheduleapp.comment.dto.CommentResponse;
import org.example.scheduleapp.comment.service.CommentService;
import org.example.scheduleapp.common.SessionConst;
import org.example.scheduleapp.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CommentCreateRequest request,
            HttpSession session
    ) {
        Long loginUserId = getLoginUserId(session);

        CommentResponse response = commentService.createComment(scheduleId, loginUserId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long scheduleId) {

        List<CommentResponse> responses = commentService.getComments(scheduleId);

        return ResponseEntity.ok(responses);
    }

    private Long getLoginUserId(HttpSession session) {

        Long loginUserId = (Long) session.getAttribute(
                SessionConst.LOGIN_USER_ID
        );

        if (loginUserId == null) {
            throw new UnauthorizedException();
        }

        return loginUserId;
    }
}
