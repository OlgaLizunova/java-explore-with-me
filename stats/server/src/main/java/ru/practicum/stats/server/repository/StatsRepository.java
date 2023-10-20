package ru.practicum.stats.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.stats.server.model.EndpointHit;
import ru.practicum.stats.server.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<EndpointHit, Long> {
    @Query(value = "select new ru.practicum.model.ViewStats(h.app, h.uri, count (distinct h.ip)) " +
            "from EndpointHit AS h " +
            "where h.uri in :uris and h.timestamp between :start and :end " +
            "group by h.uri, h.app " +
            "order by count (h.ip) desc")
    List<ViewStats> findStatsByUrisUniqueIp(
            @Param("uris") List<String> uris,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query(value = "select new ru.practicum.model.ViewStats(h.app, h.uri, count (h.ip)) " +
            "from EndpointHit AS h " +
            "where h.uri in :uris and h.timestamp between :start and :end " +
            "group by h.uri, h.app " +
            "order by count (h.ip) desc")
    List<ViewStats> findStatsByUrisNotUniqueIp(
            @Param("uris") List<String> uris,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query(value = "select new ru.practicum.model.ViewStats(h.app, h.uri, count (distinct h.ip)) " +
            "from EndpointHit AS h " +
            "where h.timestamp between :start and :end " +
            "group by h.uri, h.app " +
            "order by count (h.ip) desc")
    List<ViewStats> findStatsAllUrisUniqueIp(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query(value = "select new ru.practicum.model.ViewStats(h.app, h.uri, count (h.ip)) " +
            "from EndpointHit AS h " +
            "where h.timestamp between :start and :end " +
            "group by h.uri, h.app " +
            "order by count (h.ip) desc")
    List<ViewStats> findStatsAllUrisNotUniqueIp(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
