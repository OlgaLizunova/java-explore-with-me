package ru.practicum.main.comments.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main.comments.dto.CommentShortDto;
import ru.practicum.main.comments.dto.mapper.CommentMapper;
import ru.practicum.main.comments.dto.CommentFullDto;
import ru.practicum.main.comments.dto.NewCommentDto;
import ru.practicum.main.comments.dto.UpdateCommentUserRequest;
import ru.practicum.main.comments.model.Comment;
import ru.practicum.main.comments.repository.CommentRepository;
import ru.practicum.main.events.model.Event;
import ru.practicum.main.events.repository.EventRepository;
import ru.practicum.main.exception.NotFoundException;
import ru.practicum.main.exception.NotPublishedException;
import ru.practicum.main.user.model.User;
import ru.practicum.main.user.repository.UserRepository;
import ru.practicum.main.utils.EventState;
import ru.practicum.main.utils.PageRequestUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Transactional
    @Override
    public CommentFullDto addComment(long userId, NewCommentDto newCommentDto) {
        User author = findUserById(userId);
        LocalDateTime created = now();
        LocalDateTime updated = now();
        Event event = findEventById(newCommentDto.getEventId());
        if (event.getState() != EventState.PUBLISHED) {
            throw new NotPublishedException("Its impossible to post comment about unpublished event.");
        }
        Comment newComment = commentMapper.toComment(newCommentDto);
        newComment.setAuthor(author);
        newComment.setCreated(created);
        newComment.setUpdated(updated);
        newComment.setEvent(event);
        Comment addedComment = commentRepository.save(newComment);
        log.info("CommentService: was add comment={}", addedComment);
        return commentMapper.toCommentFullDto(addedComment);
    }

    @Transactional
    @Override
    public CommentFullDto updateComment(long userId, long commentId, UpdateCommentUserRequest newCommentDto) {
        findUserById(userId);
        Comment oldComment = findCommentByAuthorIdAndCommentId(userId, commentId);
        Comment updatedComment = commentMapper.toComment(newCommentDto);
        updatedComment.setId(oldComment.getId());
        updatedComment.setAuthor(oldComment.getAuthor());
        updatedComment.setCreated(oldComment.getCreated());
        updatedComment.setUpdated(LocalDateTime.now());
        return commentMapper.toCommentFullDto(updatedComment);
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        findUserById(userId);
        findCommentByAuthorIdAndCommentId(userId, commentId);
        commentRepository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentFullDto getCommentById(long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("Comment with id=%d was not found"));
        return commentMapper.toCommentFullDto(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<CommentShortDto> getCommentsAboutEvent(Long eventId, int from, int size) {
        Pageable pageable = PageRequestUtil.of(from, size);
        List<Comment> comments;
        eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException("Event with id=%d was not found"));
        comments = commentRepository.findCommentsByEventId(eventId, pageable);
        return commentMapper.toCommentsShortDtoList(comments);
    }

    private User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with id=%d was not found", userId)));
    }

    private Event findEventById(long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException(String.format("Event with id=%d was not found", eventId)));
    }

    private Comment findCommentByAuthorIdAndCommentId(long userId, long commentId) {
        return commentRepository.findByAuthor_IdAndId(userId, commentId).orElseThrow(() ->
                new NotFoundException(String
                        .format("Comment with id=%d and authorId=%d was not found", commentId, userId)));
    }
}
