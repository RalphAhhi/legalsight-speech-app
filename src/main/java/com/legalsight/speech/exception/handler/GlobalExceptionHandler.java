package com.legalsight.speech.exception.handler;

import com.legalsight.speech.dto.ErrorDto;
import com.legalsight.speech.exception.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoRecordFoundException(NoDataFoundException nde) {
        return new ResponseEntity<>(nde.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        String errorId = UUID.randomUUID().toString();
        logError(errorId, ex);
        ErrorDto errorResponse = ErrorDto.builder().errorId(errorId).errorMessage("An unexpected error occurred: Please contact support").build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logError(String errorId,Exception e){
        log.error(e.getMessage() + " " + errorId ,e);
    }
}
