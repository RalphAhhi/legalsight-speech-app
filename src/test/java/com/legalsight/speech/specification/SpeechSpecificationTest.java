package com.legalsight.speech.specification;

import com.legalsight.speech.dto.SpeechFilterDto;
import com.legalsight.speech.entity.SpeechEntity;
import com.legalsight.speech.entity.SpeechEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpeechSpecificationTest {
    @Mock
    private CriteriaQuery<?> query;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private Root<SpeechEntity> root;

    @BeforeEach
    void setup(){
        // mock all path need for the filter
        when(root.get(SpeechEntity_.AUTHOR)).thenReturn(mockPath());
        when(root.get(SpeechEntity_.SUBJECT_AREA)).thenReturn(mockPath());
        when(root.get(SpeechEntity_.CONTENT)).thenReturn(mockPath());
        when(root.get(SpeechEntity_.SPEECH_DATE)).thenReturn(mockPath());
    }
    @Test
    void testAuthorFilterEqualsSpecification(){
        SpeechFilterDto filterDto = new SpeechFilterDto();
        filterDto.setAuthor("testAuthor");
        SpeechSpecification speechSpecification = new SpeechSpecification(filterDto);

        speechSpecification.toPredicate(root, query, criteriaBuilder);

        //verify criteria builder create predicate for equals of author
        verify(criteriaBuilder, times(1)).equal(root.get(SpeechEntity_.AUTHOR),filterDto.getAuthor());
    }

    @Test
    void testSubjectFilterEqualsSpecification(){
        SpeechFilterDto filterDto = new SpeechFilterDto();
        filterDto.setSubjectArea("test-Subject-area");
        SpeechSpecification speechSpecification = new SpeechSpecification(filterDto);

        speechSpecification.toPredicate(root, query, criteriaBuilder);

        //verify criteria builder create predicate for equals of author
        verify(criteriaBuilder, times(1)).equal(root.get(SpeechEntity_.SUBJECT_AREA),filterDto.getSubjectArea());
    }

    @Test
    void testContentSnippetFilterShouldTriggerLikePredicate(){
        SpeechFilterDto filterDto = new SpeechFilterDto();
        filterDto.setContentSnippet("snippet");
        SpeechSpecification speechSpecification = new SpeechSpecification(filterDto);

        speechSpecification.toPredicate(root, query, criteriaBuilder);

        //verify criteria builder create predicate for equals of author
        verify(criteriaBuilder, times(1)).like(root.get(SpeechEntity_.CONTENT),"%"+filterDto.getContentSnippet()+"%");
    }

    @Test
    void testSpeechDateFilterToCreateDateRangeWhenAvailable(){
        SpeechFilterDto filterDto = new SpeechFilterDto();
        filterDto.setSpeechDateFrom(LocalDate.now());
        filterDto.setSpeechDateTo(LocalDate.now().plusDays(1L));
        SpeechSpecification speechSpecification = new SpeechSpecification(filterDto);

        speechSpecification.toPredicate(root, query, criteriaBuilder);

        //verify criteria builder create predicate for equals of author
        verify(criteriaBuilder, times(1)).between(root.get(SpeechEntity_.SPEECH_DATE),filterDto.getSpeechDateFrom(),filterDto.getSpeechDateTo());
    }

    @Test
    void testSpeechDateFilterToCreateEqualsPredicateWhenOnlyOneIsAvailable(){
        SpeechFilterDto filterDto = new SpeechFilterDto();
        filterDto.setSpeechDateFrom(LocalDate.now());
        SpeechSpecification speechSpecification = new SpeechSpecification(filterDto);

        speechSpecification.toPredicate(root, query, criteriaBuilder);

        //verify criteria builder create predicate for equals of author
        verify(criteriaBuilder, times(1)).equal(root.get(SpeechEntity_.SPEECH_DATE),filterDto.getSpeechDateFrom());
    }


    Path<Object> mockPath(){
        return mock(Path.class);
    }

}