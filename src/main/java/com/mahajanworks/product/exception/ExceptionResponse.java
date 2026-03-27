package com.mahajanworks.product.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    private String apiPath;
    private HttpStatus statusCode;
    private String message;
    private LocalDateTime timestamp;

}
