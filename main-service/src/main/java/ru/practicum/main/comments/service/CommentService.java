package ru.practicum.main.comments.service;

import ru.practicum.main.comments.dto.CommentFullDto;
import ru.practicum.main.comments.dto.CommentShortDto;
import ru.practicum.main.comments.dto.NewCommentDto;
import ru.practicum.main.comments.dto.UpdateCommentUserRequest;

import java.util.Collection;

public interface CommentService {
    CommentFullDto addComment(long userId, NewCommentDto newCommentDto);

    CommentFullDto updateComment(long userId, long commentId, UpdateCommentUserRequest newCommentDto);

    void deleteComment(Long userId, Long commentId);

    CommentFullDto getCommentById(long commentId);

    Collection<CommentShortDto> getCommentsAboutEvent(Long eventId, int from, int size);
}
