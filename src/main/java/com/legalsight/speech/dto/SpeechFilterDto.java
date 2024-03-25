package com.legalsight.speech.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SpeechFilterDto extends BaseFilterDto implements Serializable {

    private String author;
    private LocalDate speechDateFrom;
    private LocalDate speechDateTo;
    private String subjectArea;
    private String contentSnippet;

}