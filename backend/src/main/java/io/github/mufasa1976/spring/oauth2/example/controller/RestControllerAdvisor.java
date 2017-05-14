package io.github.mufasa1976.spring.oauth2.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestControllerAdvisor {

  @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
  public ResponseEntity<OptimisticLockMessage> handleOptimisticLockException(ObjectOptimisticLockingFailureException exception) {
    OptimisticLockMessage message = OptimisticLockMessage.builder()
        .entityClassName(exception.getPersistentClassName())
        .identifier(exception.getIdentifier())
        .message(exception.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(message);
  }

  @Value
  @Builder
  static class OptimisticLockMessage {
    private String entityClassName;
    private Object identifier;
    private String message;
  }
}
