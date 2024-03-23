package com.legalsight.speech.service;

import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.entity.SpeechEntity;
import com.legalsight.speech.exception.NoDataFoundException;
import com.legalsight.speech.mapper.BaseMapper;
import com.legalsight.speech.repository.SpeechRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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
        Long sampleId = 1L;

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

    @Test
    void givenInvalidId_whenFindById_shouldThrowNoDataFoundException(){
        //given:
        Long sampleId = 1L;

        when(speechRepository.findById(sampleId)).thenReturn(Optional.empty());
        //when:
        assertThatThrownBy(()->speechService.findById(sampleId))
                .isInstanceOf(NoDataFoundException.class)
                .hasMessageContaining("Speech with id 1 not found");
    }
    @Test
    void givenValidId_whenDeleteById_shouldTriggerRepositoryDeleteMethod(){
        //given:
        Long sampleId = 1L;

        SpeechEntity dummySpeechResult = createDummySpeechEntity();
        when(speechRepository.findById(sampleId)).thenReturn(Optional.of(dummySpeechResult));
        //when:
        speechService.deleteById(sampleId);

        //then:
        verify(speechRepository, times(1)).delete(dummySpeechResult);
    }

    @Test
    void givenInvalidId_whenDeleteById_shouldThrowNoDataFoundException(){
        //given:
        Long sampleId = 1L;

        when(speechRepository.findById(sampleId)).thenReturn(Optional.empty());
        //when:
        assertThatThrownBy(()->speechService.deleteById(sampleId))
                .isInstanceOf(NoDataFoundException.class)
                .hasMessageContaining("Speech with id 1 not found");
    }


    @Test
    void givenValidDto_whenCreate_shouldMapToEntityAndTriggerRepositoryToSaveEntity(){
        //given:
        SpeechEntity dummySpeechEntity = createDummySpeechEntity();
        SpeechDto sampleSpeechDto = createDummyDto();
        when(speechMapper.toEntity(sampleSpeechDto)).thenReturn(dummySpeechEntity);
        //when:
        speechService.create(sampleSpeechDto);

        //then:
        verify(speechMapper, times(1)).toDto((SpeechEntity) any());
        verify(speechRepository,times(1)).save(dummySpeechEntity);
    }

    private SpeechEntity createDummySpeechEntity(){
        SpeechEntity entity = new SpeechEntity();
        entity.setId(1L);
        entity.setAuthor("test-author");
        entity.setContent("This is my speech content");
        entity.setSpeechDate(LocalDate.now());
        entity.setSubjectArea("test-subject");
        return entity;
    }

    private SpeechDto createDummyDto(){
        SpeechDto dto = new SpeechDto();
        dto.setId(1L);
        dto.setAuthor("test-author");
        dto.setContent("This is my speech content");
        dto.setSpeechDate(LocalDate.now());
        dto.setSubjectArea("test-subject");
        return dto;
    }
}