package com.mka.filters.backend.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final HttpStatus status;
    public NotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    
}
