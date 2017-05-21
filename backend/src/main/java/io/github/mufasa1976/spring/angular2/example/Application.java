package io.github.mufasa1976.spring.angular2.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
  public static void main(String... args) {
    new SpringApplicationBuilder(Application.class)
        .web(true)
        .run(args);
  }
}
