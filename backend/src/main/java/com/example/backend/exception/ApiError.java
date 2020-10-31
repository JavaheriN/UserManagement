package com.example.backend.exception;

import lombok.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class ApiError {

    private HttpStatus status;
    private String message;
    private Throwable Cause;

    public ApiError(HttpStatus status) {
        setStatus(status);
    }

    public ApiError(HttpStatus status, RuntimeException ex) {
        setStatus(status);
        setMessage(ex.getMessage());
    }
}
