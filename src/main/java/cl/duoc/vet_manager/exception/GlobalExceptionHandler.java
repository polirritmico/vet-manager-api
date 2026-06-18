/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.vet_manager.exception;

import cl.duoc.vet_manager.exception.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private final JsonParser jsonParser = JsonParserFactory.getJsonParser();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("Param validation failed: {}", ex);
        return ResponseEntity.badRequest()
                .body(ex.getBindingResult().getFieldErrors().stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                FieldError::getDefaultMessage,
                                (prevErr, newErr) -> prevErr + ", " + newErr)));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        log.error("Resource not found at {}: {}", req.getRequestURI(), ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex, req);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceAccessException(
            ResourceAccessException ex, HttpServletRequest req) {
        log.error("Downstream service unreachable at {}: {}", req.getRequestURI(), ex.getMessage(), ex);
        ResourceAccessException secureEx = new ResourceAccessException("A required downstream service is unreachable");
        return buildErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, secureEx, req);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException ex, HttpServletRequest req) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        String responseBody = ex.getResponseBodyAsString();
        try {
            Map<String, Object> responseMap = jsonParser.parseMap(responseBody);
            if (!responseMap.containsKey("timestamp") && !responseMap.containsKey("kind")) {
                return ResponseEntity.status(status)
                        .header("Content-Type", "application/json")
                        .body(responseBody);
            }
        } catch (Exception ignored) {
        }
        log.error("Downstream client error at {}: {}", req.getRequestURI(), status);
        return buildErrorResponse(status, ex, req);
    }

    private ResponseEntity<ApiErrorResponse> buildErrorResponse(
            HttpStatus status, RuntimeException ex, HttpServletRequest req) {
        return ResponseEntity.status(status)
                .body(ApiErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(status.value())
                        .error(status.getReasonPhrase())
                        .message(ex.getMessage())
                        .path(req.getRequestURI())
                        .kind(ex.getClass().getSimpleName())
                        .build());
    }
}
