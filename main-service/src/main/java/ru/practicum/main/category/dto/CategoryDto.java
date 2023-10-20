package ru.practicum.main.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotEmpty(message = "Error: name must not be empty.")
    @Size(min = 1, message = "Error: name length too short(<1).")
    @Size(max = 50, message = "Error: name length too long(>50).")
    private String name;
}
