package io.github.mufasa1976.spring.oauth2.example.controller;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.mufasa1976.spring.oauth2.example.resource.HelloWorldResource;
import io.github.mufasa1976.spring.oauth2.example.service.HelloWorldService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/helloWorld")
@RequiredArgsConstructor
public class HelloWorldController {

  private final HelloWorldService helloWorldService;

  @GetMapping
  public PagedResources<HelloWorldResource> findAll(Pageable pageable, @RequestParam Optional<String> filter) {
    return helloWorldService.readAll(pageable, filter);
  }

  @GetMapping("{id}")
  public ResponseEntity<HelloWorldResource> read(@PathVariable Long id) {
    return helloWorldService.read(id)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
  }

  @PutMapping("{id}")
  public ResponseEntity<HelloWorldResource> update(@PathVariable Long id, @RequestBody HelloWorldResource resource) {
    return helloWorldService.update(id, resource)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public HelloWorldResource create(@RequestBody HelloWorldResource resource) {
    return helloWorldService.create(resource);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    helloWorldService.delete(id);
  }

}
