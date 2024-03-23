package com.legalsight.speech.dto;

import lombok.Data;

@Data
public abstract class BaseFilterDto {
    private String sortOrder;
    private String sortBy;
    private int page;
    private int perPage;

}