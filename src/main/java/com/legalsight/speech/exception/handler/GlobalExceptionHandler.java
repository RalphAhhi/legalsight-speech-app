package com.legalsight.speech.exception.handler;

import com.legalsight.speech.dto.ErrorDto;
import com.legalsight.speech.exception.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.UUID;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorDto> handleNoRecordFoundException(NoDataFoundException nde) {
        logError(nde.getErrorId(), nde);
        ErrorDto errorResponse = ErrorDto.builder().errorId(nde.getErrorId())
                .errorMessage(nde.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception ex) {
        String errorId = UUID.randomUUID().toString();
        logError(errorId, ex);
        ErrorDto errorResponse = ErrorDto.builder().errorId(errorId)
                .errorMessage("An unexpected error occurred: Please contact support").build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDto> handleNoResourceFoundException(NoResourceFoundException ex) {
        String errorId = UUID.randomUUID().toString();
        logError(errorId, ex);
        ErrorDto errorResponse = ErrorDto.builder().errorId(errorId)
                .errorMessage("You are accessing an invalid url").build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    private void logError(String errorId,Exception e){
        log.error(e.getMessage() + " " + errorId ,e);
    }
}