package com.legalsight.speech.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpeechDto extends BaseDto {
    @NotBlank
    private String author;
    @NotBlank
    private String content;
}