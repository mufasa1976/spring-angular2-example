package io.github.mufasa1976.spring.angular2.example.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Service;

import io.github.mufasa1976.spring.angular2.example.assembler.HeroResourceAssembler;
import io.github.mufasa1976.spring.angular2.example.model.HeroEntity;
import io.github.mufasa1976.spring.angular2.example.repository.HeroRepository;
import io.github.mufasa1976.spring.angular2.example.resource.HeroResource;
import io.github.mufasa1976.spring.angular2.example.service.HeroService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {

  private final PagedResourcesAssembler<HeroEntity> pagedResourcesAssembler;
  private final HeroResourceAssembler resourceAssembler;
  private final HeroRepository repository;

  @Override
  public PagedResources<HeroResource> readAll(Pageable pageable) {
    Page<HeroEntity> page = repository.findAll(pageable);
    return pagedResourcesAssembler.toResource(page, resourceAssembler);
  }

  @Override
  public Optional<HeroResource> read(Long id) {
    return Optional.ofNullable(repository.findOne(id))
        .map(resourceAssembler::toResource);
  }

}
