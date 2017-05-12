package io.github.mufasa1976.spring.oauth2.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {

  @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
  public ResponseEntity<String> handleOptimisticLockException(ObjectOptimisticLockingFailureException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(exception.getMessage());
  }

}
