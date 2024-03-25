package com.legalsight.speech.controller;


import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.service.SpeechService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SpeechControllerTest {
    @Mock
    private SpeechService speechService;

    @InjectMocks
    private SpeechControllerImpl controller;

    @Test
    void givenSpeechDto_whenCreate_thenShouldTriggerServiceForCreateAndReturnResult() {
        SpeechDto dto = new SpeechDto();
        dto.setAuthor("test_author");
        dto.setContent("this is my speech content");
        dto.setSpeechDate(LocalDate.now());
        dto.setSubjectArea("test subject area");

        given(speechService.create(dto)).will(invocationOnMock -> {
            dto.setId(1L);
            return dto;
        });

        //when:
        ResponseEntity<SpeechDto>  response =  controller.create(dto);
        //then:
        verify(speechService, times(1)).create(any());
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1L);
    }

    @Test
    void givenIdAndDto_whenUpdateById_thenShouldTriggerServiceUpdateAndReturnValue() {
        long speechId = 1L;

        SpeechDto dto = new SpeechDto();
        dto.setId(speechId);
        dto.setAuthor("test_author");
        dto.setContent("this is my speech content");
        dto.setSpeechDate(LocalDate.now());
        dto.setSubjectArea("test subject area");

        when(speechService.update(speechId,dto)).thenReturn(dto);
        //when:
        ResponseEntity<SpeechDto> response = controller.update(speechId, dto);
        //then:
        verify(speechService, times(1)).update(speechId,dto);

        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(dto.getId());
    }

    @Test
    void givenId_whenFindById_thenShouldTriggerServiceFindById() {
        long speechId = 1L;

        SpeechDto dto = new SpeechDto();
        dto.setId(speechId);
        dto.setAuthor("test_author");
        dto.setContent("this is my speech content");
        dto.setSpeechDate(LocalDate.now());
        dto.setSubjectArea("test subject area");

        when(speechService.findById(speechId)).thenReturn(dto);
        //when:
        ResponseEntity<SpeechDto> response = controller.findById(speechId);
        //then:
        verify(speechService, times(1)).findById(speechId);

        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(dto.getId());
    }

    @Test
    void givenId_whenDeleteById_thenShouldTriggerServiceDeleteById() {
        long speechId = 1L;
        //when:
        controller.deleteById(speechId);
        //then:
        verify(speechService, times(1)).deleteById(speechId);
    }

}