package io.github.mufasa1976.spring.oauth2.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //@formatter:off
    http
        .authorizeRequests()
          .antMatchers("/h2-console").permitAll()
          .antMatchers("/h2-console/").permitAll()
          .antMatchers("/h2-console/**").permitAll()
          .antMatchers("/api/**").authenticated()
          .anyRequest().permitAll()
        .and()
        .httpBasic()
        .and()
        .headers()
          .frameOptions()
            .disable()
        .and()
        .csrf()
          .disable();
    //@formatter:on
  }

}
