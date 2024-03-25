package com.legalsight.speech.specification;

import com.legalsight.speech.dto.SpeechFilterDto;
import com.legalsight.speech.entity.SpeechEntity;
import com.legalsight.speech.entity.SpeechEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class SpeechSpecification extends BaseSpecification<SpeechEntity> {
    private final SpeechFilterDto speechFilterDto;
    @Override
    public Predicate toPredicate(Root<SpeechEntity> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(generateEqualsPredicateIfPresentElseNull(speechFilterDto.getAuthor(), root.get(SpeechEntity_.AUTHOR), criteriaBuilder));
        predicateList.add(generateEqualsPredicateIfPresentElseNull(speechFilterDto.getSubjectArea(), root.get(SpeechEntity_.SUBJECT_AREA), criteriaBuilder));
        predicateList.add(generateLikePredicateIfPresentElseNull(speechFilterDto.getContentSnippet(), root.get(SpeechEntity_.CONTENT), criteriaBuilder));
        predicateList.add(generateDateRangePredicateIfPresentElseNull(speechFilterDto.getSpeechDateFrom(), speechFilterDto.getSpeechDateTo(),root.get(SpeechEntity_.SPEECH_DATE), criteriaBuilder));

        return criteriaBuilder.and(predicateList.stream().filter(Objects::nonNull).toArray(Predicate[]::new));
    }


}