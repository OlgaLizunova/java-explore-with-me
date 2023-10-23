package ru.practicum.main.comments.dto;

import lombok.*;
import ru.practicum.main.events.dto.EventShortDto;
import ru.practicum.main.user.dto.UserShortDto;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentFullDto {

    private Long id;
    private UserShortDto author;
    private EventShortDto event;
    private String text;
    private String created;
    private String updated;
}
