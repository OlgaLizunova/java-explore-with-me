package ru.practicum.main.comments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCommentDto {
    @NotEmpty(message = "Error: field not be empty.")
    @Size(min = 3, message = "Error: text length too short.")
    @Size(max = 10200, message = "Error: text length too long.")
    private String text;
    @JsonProperty("event")
    @Positive(message = "Error: eventId must be positive")
    private Long eventId;
}
