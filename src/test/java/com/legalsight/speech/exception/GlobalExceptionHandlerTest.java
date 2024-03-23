package com.legalsight.speech.exception;

import com.legalsight.speech.dto.ErrorDto;
import com.legalsight.speech.exception.handler.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void givenGenericException_whenHandleGenericException_errorMessageShouldBeStandardAndErrorIdShouldBePresentWithInternalServerErrorHttpStatus(){
        Exception exception = new Exception("sample error");
        ResponseEntity<ErrorDto> errorDtoResponse = globalExceptionHandler.handleGenericException(exception);
        assertThat(errorDtoResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        ErrorDto errorDto = errorDtoResponse.getBody();
        assertThat(errorDto).isNotNull();
        assertThat(errorDto.getErrorId()).isNotNull();
        assertThat(errorDto.getErrorMessage()).isEqualTo("An unexpected error occurred: Please contact support");
    }

    @Test
    void givenNoDataFoundException_whenHandleNoRecordFoundException_errorMessageFromErrorMessageAndIdShouldBePresentWithNotFoundHttpStatus(){
        NoDataFoundException noDataFoundException = new NoDataFoundException("No Data Found for Id");
        ResponseEntity<ErrorDto> errorDtoResponse = globalExceptionHandler.handleNoRecordFoundException(noDataFoundException);
        assertThat(errorDtoResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        ErrorDto errorDto = errorDtoResponse.getBody();
        assertThat(errorDto).isNotNull();
        assertThat(errorDto.getErrorId()).isNotNull();
        assertThat(errorDto.getErrorMessage()).isEqualTo(noDataFoundException.getMessage());
    }

    @Test
    void givenNoResourceException_whenHandleNoResourceFoundException_httpStatusShouldBeNotFound(){
        NoResourceFoundException noResourceFoundException = new NoResourceFoundException(HttpMethod.GET,"/");
        ResponseEntity<ErrorDto> errorDtoResponse = globalExceptionHandler.handleNoResourceFoundException(noResourceFoundException);
        assertThat(errorDtoResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        ErrorDto errorDto = errorDtoResponse.getBody();
        assertThat(errorDto).isNotNull();
        assertThat(errorDto.getErrorId()).isNotNull();
        assertThat(errorDto.getErrorMessage()).isEqualTo("You are accessing an invalid url");
    }
}