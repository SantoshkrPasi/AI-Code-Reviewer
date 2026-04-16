package com.aicode.ai_code_reviewer.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex) {
        return "Something went wrong: " + ex.getMessage();
    }
}
