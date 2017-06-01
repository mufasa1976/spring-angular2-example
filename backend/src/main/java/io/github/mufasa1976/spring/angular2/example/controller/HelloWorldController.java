package io.github.mufasa1976.spring.angular2.example.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.mufasa1976.spring.angular2.example.resource.HelloWorldResource;

@RequestMapping("/api/helloWorld")
public interface HelloWorldController {

  @GetMapping
  PagedResources<HelloWorldResource> findAll(Pageable pageable);

  @GetMapping("{reference}")
  ResponseEntity<HelloWorldResource> read(@PathVariable("reference") String reference);

  @PutMapping("{reference}")
  ResponseEntity<HelloWorldResource> update(@PathVariable("reference") String reference, @RequestBody HelloWorldResource resource);

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  HelloWorldResource create(@RequestBody HelloWorldResource resource);

  @DeleteMapping("{reference}")
  void delete(@PathVariable("reference") String reference);

}
