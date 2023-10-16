package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.StatsDto;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatsMapper {
    @Mapping(target = "id", ignore = true)
    EndpointHit toEndpointHit(EndpointHitDto endpointHitDto);

    StatsDto toStatsDto(ViewStats viewStats);

    List<StatsDto> toStatsDtoList(List<ViewStats> viewStats);
}
