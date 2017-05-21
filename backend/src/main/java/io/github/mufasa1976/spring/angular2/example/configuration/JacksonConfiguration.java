package io.github.mufasa1976.spring.angular2.example.configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

  @Bean
  public Module parameterNamesModule() {
    return new ParameterNamesModule();
  }

  @Bean
  public Module jdk8Module() {
    return new Jdk8Module();
  }

  @Bean
  public Module javaTimeModule() {
    return new JavaTimeModule();
  }

}
