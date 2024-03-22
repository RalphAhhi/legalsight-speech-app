package com.legalsight.speech.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "speech")
@EqualsAndHashCode(callSuper = true)
@Data
public class SpeechEntity extends BaseEntity {
    @Column(name = "author")
    private String author;
    @Column(name = "content")
    private String content;
}
