package io.github.mufasa1976.spring.oauth2.example.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;

import io.github.mufasa1976.spring.oauth2.example.resource.HelloWorldResource;

public interface HelloWorldService {

  PagedResources<HelloWorldResource> readAll(Pageable pageable, Optional<String> filter);

  Optional<HelloWorldResource> read(Long id);

  HelloWorldResource create(HelloWorldResource resource);

  Optional<HelloWorldResource> update(Long id, HelloWorldResource resource);

  void delete(Long id);

}
