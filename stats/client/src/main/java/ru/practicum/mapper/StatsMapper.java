package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.StatsDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatsMapper {
    StatsDto toStatsDto(Object object);

    List<StatsDto> toStatsDtoList(List<Object> objects);
}
