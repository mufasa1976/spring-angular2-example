package io.github.mufasa1976.spring.oauth2.example.configuration;

import io.github.mufasa1976.spring.oauth2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.oauth2.example.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DatabaseInitializeEventListener implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired
  private HelloWorldRepository repository;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    List<HelloWorldEntity> entities = Stream.iterate(1, n -> n + 1)
        .limit(100)
        .map(this::createEntity)
        .collect(Collectors.toList());
    repository.save(entities);
  }

  private HelloWorldEntity createEntity(int n) {
    return HelloWorldEntity.builder()
        .value("Hello World " + n)
        .build();
  }
}
