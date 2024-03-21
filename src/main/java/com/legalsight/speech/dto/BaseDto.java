package com.legalsight.speech.dto;

import lombok.Data;

import java.time.Instant;
import java.time.OffsetDateTime;

@Data
public abstract class BaseDto {
    protected String id;
    protected Instant createdDate;
    protected Instant updatedDate;
}
