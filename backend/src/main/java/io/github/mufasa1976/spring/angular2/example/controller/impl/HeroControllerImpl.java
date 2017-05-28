package io.github.mufasa1976.spring.angular2.example.controller.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.mufasa1976.spring.angular2.example.controller.HeroController;
import io.github.mufasa1976.spring.angular2.example.resource.HeroResource;
import io.github.mufasa1976.spring.angular2.example.service.HeroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HeroControllerImpl implements HeroController {

  private final HeroService heroService;

  @Override
  public PagedResources<HeroResource> findAll(Pageable pageable) {
    return heroService.readAll(pageable);
  }

  @Override
  public ResponseEntity<HeroResource> read(@PathVariable Long id) {
    return heroService.read(id)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
  }
}
