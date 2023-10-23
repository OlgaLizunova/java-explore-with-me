package ru.practicum.main.comments.dto;

import lombok.*;
import ru.practicum.main.user.dto.UserShortDto;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentShortDto {
    private Long id;
    private UserShortDto author;
    private String text;
    private String created;
    private String updated;
}
