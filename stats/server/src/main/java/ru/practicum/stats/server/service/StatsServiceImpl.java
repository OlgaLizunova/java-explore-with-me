package ru.practicum.stats.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.dto.StatsDto;
import ru.practicum.stats.server.exceptions.InvalidDateRangeException;
import ru.practicum.stats.server.mapper.StatsMapper;
import ru.practicum.stats.server.model.EndpointHit;
import ru.practicum.stats.server.model.ViewStats;
import ru.practicum.stats.server.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;
    private final StatsMapper statsMapper;

    @Override
    public void addHitStats(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = statsMapper.toEndpointHit(endpointHitDto);
        statsRepository.save(endpointHit);
        log.info("StatsService: added endpointHit={}", endpointHit);
    }

    @Override
    public List<StatsDto> getViewStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean isUniq) {
        checkStartEndRanges(start, end);
        List<StatsDto> resultStatsDtoList;
        List<ViewStats> viewStats;
        if (uris != null && !uris.isEmpty()) {
            if (isUniq) {
                viewStats = statsRepository.findStatsByUrisUniqueIp(uris, start, end);
            } else {
                viewStats = statsRepository.findStatsByUrisNotUniqueIp(uris, start, end);
            }
        } else {
            if (isUniq) {
                viewStats = statsRepository.findStatsAllUrisUniqueIp(start, end);
            } else {
                viewStats = statsRepository.findStatsAllUrisNotUniqueIp(start, end);
            }
        }

        log.info("StatsService: returned all {} viewStats", viewStats.size());
        resultStatsDtoList = statsMapper.toStatsDtoList(viewStats);
        return resultStatsDtoList;
    }

    private void checkStartEndRanges(LocalDateTime start, LocalDateTime end) {
        if (end == null || start == null || end.isBefore(start)) {
            throw new InvalidDateRangeException("Date range is invalid");
        }
    }
}
