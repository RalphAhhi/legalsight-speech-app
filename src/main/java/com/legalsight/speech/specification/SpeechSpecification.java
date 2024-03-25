package com.legalsight.speech.specification;

import com.legalsight.speech.dto.SpeechFilterDto;
import com.legalsight.speech.entity.SpeechEntity;
import com.legalsight.speech.entity.SpeechEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class SpeechSpecification implements Specification<SpeechEntity> {
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

    private Predicate generateEqualsPredicateIfPresentElseNull(String key, Expression<String> rootPath, CriteriaBuilder criteriaBuilder){
        if(StringUtils.isBlank(key)) {
            return null;
        }
        return criteriaBuilder.equal(rootPath,key);
    }

    private Predicate generateLikePredicateIfPresentElseNull(String key, Expression<String> rootPath, CriteriaBuilder criteriaBuilder){
        if(StringUtils.isBlank(key)) {
            return null;
        }
        return criteriaBuilder.like(rootPath,"%"+key+"%");
    }

    private Predicate generateDateRangePredicateIfPresentElseNull(LocalDate from, LocalDate to, Expression<LocalDate> rootPath, CriteriaBuilder criteriaBuilder){
        if (from == null && to == null) {
            return null; // No date range specified
        }
        if (from != null && to != null) {
            return criteriaBuilder.between(rootPath, from, to);
        }
        return criteriaBuilder.equal(rootPath, from != null ? from : to);
    }

}