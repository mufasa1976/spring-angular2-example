package io.github.mufasa1976.spring.angular2.example.controller;

import io.github.mufasa1976.spring.angular2.example.resource.HeroResource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/heroes")
public interface HeroController {

  @GetMapping
  PagedResources<HeroResource> findAll(Pageable pageable, String name);

  @GetMapping("{reference}")
  ResponseEntity<HeroResource> read(@PathVariable("reference") String reference);

  @PutMapping("{reference}")
  ResponseEntity<HeroResource> update(@PathVariable("reference") String reference, @RequestBody HeroResource heroResource);

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  HeroResource create(@RequestBody HeroResource heroResource);

  @DeleteMapping("{reference}")
  void delete(@PathVariable("reference") String reference);
}
