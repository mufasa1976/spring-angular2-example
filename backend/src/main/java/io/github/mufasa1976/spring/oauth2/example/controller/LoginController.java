package io.github.mufasa1976.spring.oauth2.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;
import lombok.Value;

public interface LoginController {
  @RequestMapping(path = "/api/userinfo", method = { RequestMethod.GET, RequestMethod.POST })
  UserDetails success();

  @PostMapping("/api/login/failure")
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  LoginFailure failure(HttpServletRequest request);

  @Value
  @RequiredArgsConstructor
  class LoginFailure {
    private final String username;
  }

}
