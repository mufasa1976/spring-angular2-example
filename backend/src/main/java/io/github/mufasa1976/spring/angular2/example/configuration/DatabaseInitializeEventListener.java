package io.github.mufasa1976.spring.angular2.example.configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.mufasa1976.spring.angular2.example.model.HelloWorldEntity;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import io.github.mufasa1976.spring.angular2.example.repository.HelloWorldRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseInitializeEventListener implements ApplicationListener<ApplicationReadyEvent> {

  private final HelloWorldRepository repository;

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
