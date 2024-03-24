package com.legalsight.speech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {
    private final String errorId;
    private final String errorMessage;
}