package com.legalsight.speech.dto;

import lombok.Getter;

import java.util.Map;


@Getter
public class FieldValidationErrorDto extends ErrorDto {
    private final Map<String,String> fieldError;

    public FieldValidationErrorDto(String errorId, String errorMessage,Map<String,String> fieldError) {
        super(errorId, errorMessage);
        this.fieldError = fieldError;
    }
}