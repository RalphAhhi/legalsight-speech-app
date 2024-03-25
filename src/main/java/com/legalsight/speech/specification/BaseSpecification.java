package com.legalsight.speech.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public abstract class BaseSpecification<T> implements Specification<T> {

    protected Predicate generateEqualsPredicateIfPresentElseNull(String key, Expression<String> rootPath, CriteriaBuilder criteriaBuilder){
        if(StringUtils.isBlank(key)) {
            return null;
        }
        return criteriaBuilder.equal(rootPath,key);
    }

    protected Predicate generateLikePredicateIfPresentElseNull(String key, Expression<String> rootPath, CriteriaBuilder criteriaBuilder){
        if(StringUtils.isBlank(key)) {
            return null;
        }
        return criteriaBuilder.like(rootPath,"%"+key+"%");
    }

    protected Predicate generateDateRangePredicateIfPresentElseNull(LocalDate from, LocalDate to, Expression<LocalDate> rootPath, CriteriaBuilder criteriaBuilder){
        if (from == null && to == null) {
            return null; // No date range specified
        }
        if (from != null && to != null) {
            return criteriaBuilder.between(rootPath, from, to);
        }
        return criteriaBuilder.equal(rootPath, from != null ? from : to);
    }

}