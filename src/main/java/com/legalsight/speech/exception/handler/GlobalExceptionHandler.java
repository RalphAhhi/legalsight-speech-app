package com.legalsight.speech.exception.handler;

import com.legalsight.speech.dto.ErrorDto;
import com.legalsight.speech.dto.FieldValidationErrorDto;
import com.legalsight.speech.exception.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorDto> handleNoRecordFoundException(NoDataFoundException nde) {
        logError(nde.getErrorId(), nde);
        ErrorDto errorResponse = new ErrorDto(nde.getErrorId(),nde.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception ex) {
        String errorId = UUID.randomUUID().toString();
        logError(errorId, ex);
        ErrorDto errorResponse = new ErrorDto(errorId,"An unexpected error occurred: Please contact support");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleMessageNotReadable(HttpMessageNotReadableException nre) {
        String errorId = UUID.randomUUID().toString();
        logError(errorId, nre);
        ErrorDto errorResponse = new ErrorDto(errorId,String.format("Invalid request format: %s", nre.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldValidationErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException me) {
        String errorId = UUID.randomUUID().toString();
        logError(errorId, me);
        Map<String, String> fieldErrors = new HashMap<>();
        me.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });
        FieldValidationErrorDto fieldValidationErrorDto = new FieldValidationErrorDto(errorId, "Request Validation Error", fieldErrors);
        return new ResponseEntity<>(fieldValidationErrorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDto> handleNoResourceFoundException(NoResourceFoundException ex) {
        String errorId = UUID.randomUUID().toString();
        logError(errorId, ex);
        ErrorDto errorResponse = new ErrorDto(errorId,"You are accessing an invalid url");

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    private void logError(String errorId,Exception e){
        log.error(e.getMessage() + " " + errorId ,e);
    }
}