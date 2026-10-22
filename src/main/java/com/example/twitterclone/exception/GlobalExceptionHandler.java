package com.example.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<Map<String, String>> handleResourceNotFound(
        ResourceNotFoundException exception) {

    Map<String, String> error = Map.of(
            "message", exception.getMessage()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
}
@ExceptionHandler(DuplicateResourceException.class)
public ResponseEntity<Map<String, String>> handleDuplicateResource(
        DuplicateResourceException exception) {

    Map<String, String> error = Map.of(
            "message", exception.getMessage()
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> handleValidationErrors(
        MethodArgumentNotValidException exception) {

    Map<String, String> fieldErrors = new LinkedHashMap<>();

    exception.getBindingResult().getFieldErrors().forEach(error ->
            fieldErrors.putIfAbsent(
                    error.getField(),
                    error.getDefaultMessage()
            )
    );

    Map<String, Object> response = new LinkedHashMap<>();
    response.put("message", "Validation failed");
    response.put("errors", fieldErrors);

    return ResponseEntity.badRequest().body(response);
}
@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(
        DataIntegrityViolationException exception) {

    Map<String, String> error = Map.of(
            "message", "Username or email already exists"
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
}

}
