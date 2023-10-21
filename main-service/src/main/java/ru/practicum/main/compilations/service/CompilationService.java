package ru.practicum.main.compilations.service;

import ru.practicum.main.compilations.dto.CompilationDto;
import ru.practicum.main.compilations.dto.NewCompilationDto;
import ru.practicum.main.compilations.dto.UpdateCompilationRequest;

import java.util.Collection;

public interface CompilationService {
    CompilationDto addCompilationAdmin(NewCompilationDto newCompilationDto);

    void deleteCompilationAdmin(Long compId);

    CompilationDto updateCompilationAdmin(Long compId, UpdateCompilationRequest updateCompilationRequest);

    Collection<CompilationDto> getAllCompilationsPub(boolean pinned, int from, int size);

    CompilationDto getCompilationByIdPub(Long compId);
}
