package ru.practicum.main.comments.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.comments.dto.CommentFullDto;
import ru.practicum.main.comments.dto.CommentShortDto;
import ru.practicum.main.comments.service.CommentService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;

import static ru.practicum.main.utils.PageRequestUtil.DEFAULT_FROM;
import static ru.practicum.main.utils.PageRequestUtil.DEFAULT_SIZE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/comments")
public class CommentPubController {
    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentFullDto> getCommentById(@PathVariable(name = "commentId") @Positive long commentId) {
        log.info("Received GET request for comment with id: {} ", commentId);
        CommentFullDto commentFullDto = commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentFullDto, HttpStatus.OK);
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<Collection<CommentShortDto>> getCommentsAboutEvent(
            @PathVariable(name = "eventId") @Positive Long eventId,
            @RequestParam(name = "from", required = false, defaultValue = DEFAULT_FROM) @PositiveOrZero int from,
            @RequestParam(name = "size", required = false, defaultValue = DEFAULT_SIZE) @Positive int size) {
        log.info("Received GET request for comments about event with id: {} ", eventId);
        Collection<CommentShortDto> commentsAboutEvent = commentService.getCommentsAboutEvent(eventId, from, size);
        return new ResponseEntity<>(commentsAboutEvent, HttpStatus.OK);
    }
}
