package com.legalsight.speech.controller;

import com.legalsight.speech.dto.ResultSetResponse;
import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.dto.SpeechFilterDto;
import com.legalsight.speech.service.SpeechService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SpeechControllerImpl implements SpeechController {

    private final SpeechService speechService;
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<SpeechDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(speechService.findById(id));
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        speechService.deleteById(id);
        return ResponseEntity.ok().build() ;
    }

    @PostMapping
    @Override
    public ResponseEntity<SpeechDto> create(SpeechDto speechDto) {
        return ResponseEntity.ok(speechService.create(speechDto));
    }

    @PutMapping
    @Override
    public ResponseEntity<SpeechDto> update(Long id, SpeechDto speechDto) {
        return ResponseEntity.ok(speechService.update(id,speechDto));
    }

    @GetMapping
    @Override
    public ResponseEntity<ResultSetResponse<SpeechDto>> list(int perPage, int page, String sortBy, String sortOrder) {
        SpeechFilterDto filterDto = new SpeechFilterDto();
        filterDto.setPage(page);
        filterDto.setPerPage(perPage);
        filterDto.setSortBy(sortBy);
        filterDto.setSortOrder(sortOrder);
        return ResponseEntity.ok(new ResultSetResponse<>(speechService.list(filterDto)));
    }

}