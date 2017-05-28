package io.github.mufasa1976.spring.angular2.example.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import io.github.mufasa1976.spring.angular2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.angular2.example.model.HeroEntity;
import io.github.mufasa1976.spring.angular2.example.repository.HelloWorldRepository;
import io.github.mufasa1976.spring.angular2.example.repository.HeroRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseInitializeEventListener implements ApplicationListener<ApplicationReadyEvent> {

  private final HelloWorldRepository helloWorldRepository;
  private final HeroRepository heroRepository;

  private final List<String> HEROES = Arrays.asList(
    "Mr. Nice",
      "Narco",
      "Bombasto",
      "Celeritas",
      "Magneta",
      "RubberMan",
      "Dynama",
      "Dr IQ",
      "Magma",
      "Tornado"
  );

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    addHelloWorld();
    addHeroes();
  }

  private void addHelloWorld() {
    List<HelloWorldEntity> entities = Stream.iterate(1, n -> n + 1)
        .limit(100)
        .map(this::createEntity)
        .collect(Collectors.toList());
    helloWorldRepository.save(entities);
  }

  private HelloWorldEntity createEntity(int n) {
    return HelloWorldEntity.builder()
        .value("Hello World " + n)
        .build();
  }

  private void addHeroes() {
    HEROES.stream()
        .map(name -> HeroEntity.builder().name(name).build())
        .forEach(heroRepository::save);
  }
}
