package com.legalsight.speech.service;

import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.entity.SpeechEntity;
import com.legalsight.speech.mapper.BaseMapper;
import com.legalsight.speech.repository.SpeechRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpeechServiceTest {

    @InjectMocks
    private SpeechService speechService;
    @Mock
    private SpeechRepository speechRepository;
    @Mock
    private BaseMapper<SpeechDto, SpeechEntity> speechMapper;
    @Test
    void givenValidId_whenFindById_shouldReturnDataRelatedToId(){
        //given:
        String sampleId = UUID.randomUUID().toString();

        SpeechEntity dummySpeechResult = createDummySpeechEntity();
        SpeechDto mappedDummySpeechResult = createDummyDto();
        when(speechRepository.findById(sampleId)).thenReturn(Optional.of(dummySpeechResult));
        when(speechMapper.toDto(dummySpeechResult)).thenReturn(mappedDummySpeechResult);
        //when:
        SpeechDto result = speechService.findById(sampleId);

        //then:
        verify(speechMapper, times(1)).toDto(dummySpeechResult);
        assertThat(result).isEqualTo(mappedDummySpeechResult);
    }

    private SpeechEntity createDummySpeechEntity(){
        SpeechEntity entity = new SpeechEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setAuthor("test-author");
        entity.setContent("This is my speech content");
        return entity;
    }

    private SpeechDto createDummyDto(){
        SpeechDto dto = new SpeechDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setAuthor("test-author");
        dto.setContent("This is my speech content");
        return dto;
    }
}
