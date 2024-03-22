package com.legalsight.speech.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class ErrorDto {
    private String errorId;
    private String errorMessage;
}
