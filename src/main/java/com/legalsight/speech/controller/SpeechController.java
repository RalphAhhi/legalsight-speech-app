package com.legalsight.speech.controller;

import com.legalsight.speech.dto.ResultSetResponse;
import com.legalsight.speech.dto.SpeechDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speech")
@Validated
public interface SpeechController {
    @GetMapping("/{id}")
    ResponseEntity<SpeechDto> findById(@NotBlank @PathVariable String id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@NotBlank @PathVariable String id);

    @PostMapping
    ResponseEntity<SpeechDto> create(SpeechDto speechDto);

    @PutMapping("/{id}")
    ResponseEntity<SpeechDto> update(@NotBlank String id, SpeechDto speechDto);

    @GetMapping
    ResponseEntity<ResultSetResponse<SpeechDto>> list();

}