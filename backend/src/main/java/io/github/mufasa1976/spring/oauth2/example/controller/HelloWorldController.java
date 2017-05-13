package io.github.mufasa1976.spring.oauth2.example.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.mufasa1976.spring.oauth2.example.resource.HelloWorldResource;

@RequestMapping("/api/helloWorld")
public interface HelloWorldController {

  @RequestMapping(method = RequestMethod.GET)
  PagedResources<HelloWorldResource> findAll(Pageable pageable);

  @GetMapping("{id}")
  ResponseEntity<HelloWorldResource> read(@PathVariable("id") Long id);

  @PutMapping("{id}")
  ResponseEntity<HelloWorldResource> update(@PathVariable("id") Long id, @RequestBody HelloWorldResource resource);

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  HelloWorldResource create(@RequestBody HelloWorldResource resource);

  @DeleteMapping("{id}")
  void delete(@PathVariable("id") Long id);

}
