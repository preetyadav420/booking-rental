package com.preet.bookingrental.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationExceptionHandler(MethodArgumentNotValidException exception)
    {
        Map<String,String> errors = new HashMap<String,String>();
        exception.getBindingResult().getFieldErrors().forEach(e->{
            errors.put(e.getField(),e.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}