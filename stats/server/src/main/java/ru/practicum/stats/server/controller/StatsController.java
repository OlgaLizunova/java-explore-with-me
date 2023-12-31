package ru.practicum.stats.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.dto.StatsDto;
import ru.practicum.stats.server.service.StatsService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "")
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @PostMapping("/hit")
    public ResponseEntity<Void> addEndpointHit(@RequestBody @Valid EndpointHitDto endpointHitDto) {
        log.info("StatsController: receive POST request for add new Hit user with body={}", endpointHitDto);
        statsService.addHitStats(endpointHitDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/stats")
    public ResponseEntity<List<StatsDto>> getViewStats(
            @RequestParam(value = "start", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam(value = "end", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
            @RequestParam(value = "uris", required = false) List<String> uris,
            @RequestParam(value = "unique", defaultValue = "false", required = false) boolean isUnique
    ) {

        if (uris == null) {
            uris = Collections.emptyList();
        }
        log.info("receive GET request for return ViewStats from={}, to={}, uris={}, uniq={}", start, end, uris, isUnique);
        List<StatsDto> viewStatsDto = statsService.getViewStats(start, end, uris, isUnique);
        return new ResponseEntity<>(viewStatsDto, HttpStatus.OK);
    }


}
