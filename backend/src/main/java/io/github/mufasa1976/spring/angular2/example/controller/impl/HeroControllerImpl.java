package io.github.mufasa1976.spring.angular2.example.controller.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.mufasa1976.spring.angular2.example.controller.HeroController;
import io.github.mufasa1976.spring.angular2.example.resource.HeroResource;
import io.github.mufasa1976.spring.angular2.example.service.HeroService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class HeroControllerImpl implements HeroController {

  // The Annotation @RequestBody and @PathVariable are important because
  // Spring works either with a JDK-Proxy or with a CGlib-Proxy
  // In case of a CGlib-Proxy the Annotations of the Interface are ignored
  // and only the Annotations of the Implementation Class are recognized.

  private final HeroService heroService;

  @Override
  public PagedResources<HeroResource> findAll(Pageable pageable) {
    return heroService.readAll(pageable);
  }

  @Override
  public ResponseEntity<HeroResource> read(@PathVariable String reference) {
    return heroService.read(reference)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
  }

  @Override
  public ResponseEntity<HeroResource> update(@PathVariable String reference, @RequestBody HeroResource heroResource) {
    return heroService.update(reference, heroResource)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
  }

  @Override
  public HeroResource create(@RequestBody HeroResource heroResource) {
    return heroService.create(heroResource);
  }

  @Override
  public void delete(@PathVariable String reference) {
    heroService.delete(reference);
  }
}
