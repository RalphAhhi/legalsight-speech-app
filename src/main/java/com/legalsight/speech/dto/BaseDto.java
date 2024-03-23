package com.legalsight.speech.dto;

import lombok.Data;

import java.time.Instant;

@Data
public abstract class BaseDto {
    protected Long id;
    protected Instant createdDate;
    protected Instant updatedDate;
}