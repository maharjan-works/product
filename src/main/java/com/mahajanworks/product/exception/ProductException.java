package com.mahajanworks.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductException {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex, WebRequest webRequest) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ExceptionResponse.builder()
                        .apiPath(webRequest.getDescription(false))
                        .statusCode(HttpStatus.CONFLICT)
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }
}
