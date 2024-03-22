package com.legalsight.speech.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class LegalSpeechException extends RuntimeException {

    private final String errorId = UUID.randomUUID().toString();
    public LegalSpeechException() {
    }
    public LegalSpeechException(String message) {
        super(message);
    }
}