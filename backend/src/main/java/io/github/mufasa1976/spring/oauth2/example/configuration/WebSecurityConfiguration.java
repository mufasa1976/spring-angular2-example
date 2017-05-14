package io.github.mufasa1976.spring.oauth2.example.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableConfigurationProperties(WebSecurityConfiguration.LoginProperties.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Data
  @ConfigurationProperties("security.login")
  public static class LoginProperties {
    private String usernameParameter = "username";
    private String passwordParameter = "password";
  }

  private final LoginProperties loginProperties;

  @Value("${server.session.cookie.name:JSESSIONID}")
  private String cookieName = "JSESSIONID";

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //@formatter:off
    http
        .authorizeRequests()
          .antMatchers("/api/login/failure").permitAll()
          .antMatchers("/api/**").authenticated()
          .anyRequest().permitAll()
        .and()
        .csrf().disable()
        .headers()
          .frameOptions().disable()
        .and()
        .httpBasic()
        .and()
        .formLogin()
          .loginPage("/api/login")
          .loginProcessingUrl("/api/login")
          .successForwardUrl("/api/userinfo")
          .failureForwardUrl("/api/login/failure")
          .usernameParameter(loginProperties.getUsernameParameter())
          .passwordParameter(loginProperties.getPasswordParameter())
          .permitAll()
        .and()
        .logout()
          .logoutUrl("/api/logout")
          .deleteCookies(cookieName)
          .logoutSuccessHandler(logoutSuccessHandler())
          .permitAll();
    //@formatter:on
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT);
  }

}
