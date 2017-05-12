package io.github.mufasa1976.spring.oauth2.example.controller;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.mufasa1976.spring.oauth2.example.assembler.HelloWorldResourceAssembler;
import io.github.mufasa1976.spring.oauth2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.oauth2.example.repository.HelloWorldRepository;
import io.github.mufasa1976.spring.oauth2.example.resource.HelloWorldResource;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/helloWorld")
@RequiredArgsConstructor
public class HelloWorldController {

  private final PagedResourcesAssembler<HelloWorldEntity> pagedResourcesAssembler;
  private final HelloWorldResourceAssembler helloWorldResourceAssembler;
  private final HelloWorldRepository helloWorldRepository;

  @GetMapping("{id}")
  public Optional<HelloWorldResource> getById(@PathVariable Long id) {
    return Optional.ofNullable(helloWorldRepository.getOne(id))
        .map(helloWorldResourceAssembler::toResource);
  }

  @GetMapping
  public PagedResources<HelloWorldResource> findAll(Pageable pageable) {
    return pagedResourcesAssembler.toResource(helloWorldRepository.findAll(pageable), helloWorldResourceAssembler);
  }

}
