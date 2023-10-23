package ru.practicum.main.comments.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.practicum.main.comments.dto.*;
import ru.practicum.main.comments.model.Comment;
import ru.practicum.main.utils.LocalDateTimeMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = LocalDateTimeMapper.class)
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "event", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    Comment toComment(NewCommentDto newCommentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "event", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    Comment toComment(UpdateCommentUserRequest updateCommentUserRequest);

    CommentFullDto toCommentFullDto(Comment comment);

    @Named(value = "toCommentShortDtoList")
    CommentShortDto toCommentShortDto(Comment comment);

    List<CommentShortDto> toCommentsShortDtoList(List<Comment> comments);

    List<CommentFullDto> toCommentsFullDtoList(List<Comment> comments);
}
