package com.legalsight.speech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "speech")
public class SpeechEntity extends BaseEntity {
    private String author;
    private String content;
}
