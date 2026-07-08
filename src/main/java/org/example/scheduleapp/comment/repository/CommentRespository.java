package org.example.scheduleapp.comment.repository;

import org.example.scheduleapp.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRespository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByScheduleId(Long scheduleId);
}
