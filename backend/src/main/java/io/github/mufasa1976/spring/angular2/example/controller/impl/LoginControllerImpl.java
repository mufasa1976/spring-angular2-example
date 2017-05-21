package io.github.mufasa1976.spring.angular2.example.controller.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import io.github.mufasa1976.spring.angular2.example.configuration.WebSecurityConfiguration;
import io.github.mufasa1976.spring.angular2.example.controller.LoginController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<LoginFailure> failure(HttpServletRequest request) {
    for (Enumeration<String> headerNames = request.getHeaderNames(); headerNames.hasMoreElements();) {
      String header = headerNames.nextElement();
      log.info("{} = {}", header, request.getHeader(header));
    }
    String username = request.getParameter(loginProperties.getUsernameParameter());
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    return ResponseEntity.status(status)
        .body(new LoginFailure(
            status.value(),
            status.getReasonPhrase(),
            "Invalid Credentials",
            username,
            loginProperties.getLoginPage()));
  }

}
