package com.legalsight.speech.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PageRequestUtils {
    public static PageRequest createPageRequest(int page, int perPage, String sortBy, String sortOrder) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(page-1, perPage, Sort.by(direction, sortBy).and(Sort.by(Sort.Direction.ASC,"id")));
    }

}