package ru.practicum.main.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.main.request.model.ParticipationRequest;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<ParticipationRequest, Long> {
    Optional<List<ParticipationRequest>> findAllByRequesterId(Long requesterId);

    Optional<ParticipationRequest> findByIdAndRequesterId(Long requestId, Long requesterId);

    Optional<List<ParticipationRequest>> findAllByEventId(Long eventId);

    Optional<List<ParticipationRequest>> findAllByEventIdAndIdIn(
            Long eventId, List<Long> ids);
}
