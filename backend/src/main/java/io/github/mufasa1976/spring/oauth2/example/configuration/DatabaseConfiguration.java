package io.github.mufasa1976.spring.oauth2.example.configuration;

import io.github.mufasa1976.spring.oauth2.example.model.AbstractPersistableEntity;
import io.github.mufasa1976.spring.oauth2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.oauth2.example.repository.BaseRepository;
import io.github.mufasa1976.spring.oauth2.example.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableJpaRepositories(basePackageClasses = BaseRepository.class)
@EntityScan(basePackageClasses = AbstractPersistableEntity.class)
@EnableJpaAuditing
public class DatabaseConfiguration {
}
