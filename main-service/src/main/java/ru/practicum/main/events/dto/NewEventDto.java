package ru.practicum.main.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.main.location.model.Location;
import ru.practicum.main.utils.AfterDeltaHoursFromNow;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {
    @NotEmpty(message = "Error: annotation not be empty.")
    @Size(min = 20, message = "Error: annotation length too short.")
    @Size(max = 2000, message = "Error: annotation length too long.")
    private String annotation;
    @JsonProperty("category")
    @Positive(message = "Error: categoryId must be positive")
    private Long categoryId;
    @NotEmpty(message = "Error: must not be empty.")
    @Size(min = 20, message = "Error: description length too short.")
    @Size(max = 7000, message = "Error: description length too long.")
    private String description;
    @AfterDeltaHoursFromNow(message = "eventDate must be not before than 2 hours from now", deltaHours = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    @NotNull(message = "Error: location not be null.")
    private Location location;
    @Builder.Default
    Boolean paid = false;
    @PositiveOrZero(message = "Error: participantLimit must be zero or positive.")
    int participantLimit;
    @Builder.Default
    Boolean requestModeration = true;
    @Size(min = 3, max = 120, message = "Error: length error.")
    private String title;
}
