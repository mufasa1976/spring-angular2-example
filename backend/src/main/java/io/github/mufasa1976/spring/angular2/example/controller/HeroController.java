package io.github.mufasa1976.spring.angular2.example.controller;

import io.github.mufasa1976.spring.angular2.example.resource.HeroResource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/heroes")
public interface HeroController {

  @GetMapping
  PagedResources<HeroResource> findAll(Pageable pageable);

  @GetMapping("{id}")
  ResponseEntity<HeroResource> read(@PathVariable("id") Long id);

}
