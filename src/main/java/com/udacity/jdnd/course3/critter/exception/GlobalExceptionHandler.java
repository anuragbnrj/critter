package com.udacity.jdnd.course3.critter.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException ex,
                                                                     HttpServletRequest request) {
        CustomError customError = new CustomError();
        customError.setStatus(HttpStatus.NOT_FOUND);
        customError.setUrl(request.getRequestURL());
        customError.setMethod(request.getMethod());
        customError.setMessage(ex.getLocalizedMessage());
        customError.setTimestamp(LocalDateTime.now());
        log.error(customError.toString());
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError> handleGenericException(EntityNotFoundException ex,
                                                              HttpServletRequest request) {
        CustomError customError = new CustomError();
        customError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        customError.setUrl(request.getRequestURL());
        customError.setMethod(request.getMethod());
        customError.setMessage(ex.getLocalizedMessage());
        customError.setTimestamp(LocalDateTime.now());
        log.error(customError.toString());
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Data
    private static class CustomError {
        private HttpStatus status;
        private StringBuffer url;
        private String method;
        private String message;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss")
        private LocalDateTime timestamp;
    }

}
