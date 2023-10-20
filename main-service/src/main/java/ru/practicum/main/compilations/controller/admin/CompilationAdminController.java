package ru.practicum.main.compilations.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.compilations.dto.CompilationDto;
import ru.practicum.main.compilations.dto.NewCompilationDto;
import ru.practicum.main.compilations.dto.UpdateCompilationRequest;
import ru.practicum.main.compilations.service.CompilationService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Slf4j
@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
public class CompilationAdminController {
    private final CompilationService compilationService;

    @PostMapping
    public ResponseEntity<CompilationDto> addCompilationAdmin(
            @RequestBody @Valid @NotNull NewCompilationDto newCompilationDto
    ) {
        log.info("Received POST request for add new compilation with body={}",
                newCompilationDto);
        CompilationDto savedCompilation = compilationService.addCompilationAdmin(newCompilationDto);
        return new ResponseEntity<>(savedCompilation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<Void> deleteCompilationAdmin(
            @PathVariable(name = "compId") @Positive Long compId

    ) {
        log.info("Received DELETE request for delete compilation with compId={}", compId);
        compilationService.deleteCompilationAdmin(compId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{compId}")
    public ResponseEntity<CompilationDto> updateCompilationAdmin(
            @RequestBody @Valid @NotNull UpdateCompilationRequest updateCompilationRequest,
            @PathVariable(name = "compId") @Positive Long compId
    ) {
        log.info("Received PATCH request for update compilation with compId={}, body={}",
                compId, updateCompilationRequest);
        CompilationDto updatedCompilationDto = compilationService.updateCompilationAdmin(compId, updateCompilationRequest);
        return new ResponseEntity<>(updatedCompilationDto, HttpStatus.OK);
    }
}
