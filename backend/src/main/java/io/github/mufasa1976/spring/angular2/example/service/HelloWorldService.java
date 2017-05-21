package io.github.mufasa1976.spring.angular2.example.service;

import java.util.Optional;

import io.github.mufasa1976.spring.angular2.example.resource.HelloWorldResource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;

public interface HelloWorldService {

  PagedResources<HelloWorldResource> readAll(Pageable pageable, Optional<String> filter);

  Optional<HelloWorldResource> read(Long id);

  HelloWorldResource create(HelloWorldResource resource);

  Optional<HelloWorldResource> update(Long id, HelloWorldResource resource);

  void delete(Long id);

}
