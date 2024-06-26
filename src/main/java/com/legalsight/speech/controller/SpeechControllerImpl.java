package com.legalsight.speech.controller;

import com.legalsight.speech.dto.ResultSetResponse;
import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.dto.SpeechFilterDto;
import com.legalsight.speech.service.SpeechService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class SpeechControllerImpl implements SpeechController {

    private final SpeechService speechService;
    @Override
    public ResponseEntity<SpeechDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(speechService.findById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        speechService.deleteById(id);
        return ResponseEntity.ok().build() ;
    }

    @Override
    public ResponseEntity<SpeechDto> create(SpeechDto speechDto) {
        return ResponseEntity.ok(speechService.create(speechDto));
    }

    @Override
    public ResponseEntity<SpeechDto> update(Long id, SpeechDto speechDto) {
        return ResponseEntity.ok(speechService.update(id,speechDto));
    }

    @Override
    public ResponseEntity<ResultSetResponse<SpeechDto>> list(String author, String subjectArea, String speechContentSnippet, LocalDate speechDateFrom, LocalDate speechDateTo, int perPage, int page, String sortBy, String sortOrder) {
        SpeechFilterDto filterDto = new SpeechFilterDto();
        filterDto.setAuthor(author);
        filterDto.setContentSnippet(speechContentSnippet);
        filterDto.setSubjectArea(subjectArea);
        filterDto.setSpeechDateFrom(speechDateFrom);
        filterDto.setSpeechDateTo(speechDateTo);
        filterDto.setPage(page);
        filterDto.setPerPage(perPage);
        filterDto.setSortBy(sortBy);
        filterDto.setSortOrder(sortOrder);
        return ResponseEntity.ok(speechService.list(filterDto));
    }


}