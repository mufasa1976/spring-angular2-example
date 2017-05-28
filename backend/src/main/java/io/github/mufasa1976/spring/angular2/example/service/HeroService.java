package io.github.mufasa1976.spring.angular2.example.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;

import io.github.mufasa1976.spring.angular2.example.resource.HeroResource;

public interface HeroService {

  PagedResources<HeroResource> readAll(Pageable pageable);

  Optional<HeroResource> read(Long id);

}
