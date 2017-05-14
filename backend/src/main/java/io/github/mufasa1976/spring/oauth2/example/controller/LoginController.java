package io.github.mufasa1976.spring.oauth2.example.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.RequiredArgsConstructor;
import lombok.Value;

public interface LoginController {

  @RequestMapping(path = "/api/userinfo", method = { RequestMethod.GET, RequestMethod.POST })
  UserDetails success();

  @PostMapping("/api/login/failure")
  ResponseEntity<LoginFailure> failure(HttpServletRequest request);

  @Value
  @RequiredArgsConstructor
  @JsonPropertyOrder({ "timestamp", "status", "error", "message", "username", "path" })
  class LoginFailure {
    private final Date timestamp = new Date();
    private final int status;
    private final String error;
    private final String message;
    private final String username;
    private final String path;
  }

}
