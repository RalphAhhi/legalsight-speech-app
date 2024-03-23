package com.legalsight.speech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpeechDto extends BaseDto {
    @NotBlank
    private String author;
    @NotBlank
    private String content;
    @NotNull
    private LocalDate speechDate;
    @NotBlank
    private String subjectArea;
}