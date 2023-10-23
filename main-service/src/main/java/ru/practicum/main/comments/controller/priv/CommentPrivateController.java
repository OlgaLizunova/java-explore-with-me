package ru.practicum.main.comments.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.comments.dto.CommentFullDto;
import ru.practicum.main.comments.dto.NewCommentDto;
import ru.practicum.main.comments.dto.UpdateCommentUserRequest;
import ru.practicum.main.comments.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@RestController
@RequestMapping(path = "/users/{userId}/comments")
@RequiredArgsConstructor
public class CommentPrivateController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentFullDto> addComment(
            @RequestBody @Valid NewCommentDto newCommentDto,
            @PathVariable(name = "userId") long userId
    ) {
        log.info("Receive POST request from userId={} for add new comment with body={}", userId, newCommentDto);
        CommentFullDto savedComment = commentService.addComment(userId, newCommentDto);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentFullDto> updateComment(
            @RequestBody @Valid UpdateCommentUserRequest updateCommentDto,
            @PathVariable(name = "userId") @Positive long userId,
            @PathVariable(name = "commentId") @Positive long commentId
    ) {
        log.info("Received PATCH private request for update comment with commentId={}, for userId={}, with body={}",
                commentId, userId, updateCommentDto);
        CommentFullDto commentFullDto = commentService.updateComment(userId, commentId, updateCommentDto);
        return new ResponseEntity<>(commentFullDto, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<CommentFullDto> deleteComment(
            @PathVariable(name = "userId") @Positive long userId,
            @PathVariable(name = "commentId") @Positive long commentId
    ) {
        log.info("Received DELETE private request for delete comment with commentId={}, for userId={}",
                commentId, userId);
        commentService.deleteComment(userId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
