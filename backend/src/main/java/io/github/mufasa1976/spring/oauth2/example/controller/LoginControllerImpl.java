package io.github.mufasa1976.spring.oauth2.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import io.github.mufasa1976.spring.oauth2.example.configuration.WebSecurityConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginControllerImpl implements LoginController {

  private final WebSecurityConfiguration.LoginProperties loginProperties;

  @Override
  public UserDetails success() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (UserDetails) authentication.getPrincipal();
  }

  @Override
  public LoginFailure failure(HttpServletRequest request) {
    String username = request.getParameter(loginProperties.getUsernameParameter());
    return new LoginFailure(username);
  }

}
