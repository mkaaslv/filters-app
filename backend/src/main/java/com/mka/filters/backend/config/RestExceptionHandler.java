package com.mka.filters.backend.config;

import com.mka.filters.backend.dtos.ErrorDto;
import com.mka.filters.backend.exceptions.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { NotFoundException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(NotFoundException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(new ErrorDto(exception.getMessage()));

    }
}
