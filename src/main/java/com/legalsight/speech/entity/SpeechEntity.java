package com.legalsight.speech.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "speech")
@EqualsAndHashCode(callSuper = true)
@Data
public class SpeechEntity extends BaseEntity {
    @Column(name = "author", length = 70)
    private String author;
    @Column(name = "content")
    private String content;
    @Column(name = "speech_date")
    private LocalDate speechDate;
    @Column(name = "subject_area")
    private String subjectArea;
}