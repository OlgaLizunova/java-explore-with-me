package ru.practicum.main.comments.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.main.comments.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByEventId(Long eventId, Pageable pageable);

    Optional<Comment> findByAuthor_IdAndId(long userId, long commentId);
}
