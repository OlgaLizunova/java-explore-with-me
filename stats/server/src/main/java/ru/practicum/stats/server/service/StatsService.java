package ru.practicum.stats.server.service;

import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    void addHitStats(EndpointHitDto endpointHitDto);

    List<StatsDto> getViewStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean isUniq);
}
