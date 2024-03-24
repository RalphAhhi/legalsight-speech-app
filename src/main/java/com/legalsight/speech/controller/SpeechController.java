package com.legalsight.speech.controller;

import com.legalsight.speech.dto.ResultSetResponse;
import com.legalsight.speech.dto.SpeechDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speech")
@Validated
public interface SpeechController {
    @GetMapping("/{id}")
    @Operation(summary = "Find Speech by id API", description = "Find an existing Speech.", tags = "Speech")
    ResponseEntity<SpeechDto> findById(@NotNull @PathVariable Long id);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Speech by id API", description = "Delete an existing Speech.", tags = "Speech")
    ResponseEntity<Void> deleteById(@NotNull @PathVariable Long id);

    @PostMapping
    @Operation(summary = "Create Speech API", description = "Creates a new speech record with the provided details.", tags = "Speech")
    ResponseEntity<SpeechDto> create(@Valid @RequestBody SpeechDto speechDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update Speech API", description = "Update an existing speech record by id with the provided details.", tags = "Speech")
    ResponseEntity<SpeechDto> update(@NotNull Long id, @RequestBody @Valid SpeechDto speechDto);

    @GetMapping
    @Operation(summary = "List Speech API with filter", description = "List All Speech base on criteria. Default will list all.", tags = "Speech")
    ResponseEntity<ResultSetResponse<SpeechDto>> list(@RequestParam(value = "per_page", defaultValue = "10") @Min(1) @Max(100) final int perPage,
                                                      @RequestParam(value = "page", defaultValue = "1") @Min(1) @Max(Integer.MAX_VALUE) final int page,
                                                      @RequestParam(value = "sortBy", defaultValue = "createdDate") final String sortBy,
                                                      @RequestParam(value = "sortOrder", defaultValue = "asc") final String sortOrder);

}