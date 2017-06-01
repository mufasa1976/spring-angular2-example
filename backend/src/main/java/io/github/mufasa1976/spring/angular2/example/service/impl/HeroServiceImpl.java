package io.github.mufasa1976.spring.angular2.example.service.impl;

import java.util.Optional;

import io.github.mufasa1976.spring.angular2.example.assembler.HelloWorldResourceAssembler;
import io.github.mufasa1976.spring.angular2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.angular2.example.repository.HelloWorldRepository;
import org.modelmapper.ModelMapper;
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
import org.springframework.ui.ModelMap;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
class HeroServiceImpl extends AbstractServiceImpl<HeroEntity, HeroResource, HeroRepository> implements HeroService {

  private final PagedResourcesAssembler<HeroEntity> pagedResourcesAssembler;
  private final HeroResourceAssembler resourceAssembler;

  HeroServiceImpl(
      final ModelMapper modelMapper,
      final HeroRepository repository,
      final EntityManager entityManager,
      final PagedResourcesAssembler<HeroEntity> pagedResourcesAssembler,
      final HeroResourceAssembler resourceAssembler) {
    super(modelMapper, HeroEntity.class, repository, entityManager);
    this.pagedResourcesAssembler = pagedResourcesAssembler;
    this.resourceAssembler = resourceAssembler;
  }

  @Override
  public PagedResources<HeroResource> readAll(Pageable pageable) {
    return pagedResourcesAssembler.toResource(findAll(pageable), resourceAssembler);
  }

  @Override
  public Optional<HeroResource> read(String reference) {
    return findByReference(reference)
        .map(resourceAssembler::toResource);
  }

  @Override
  @Transactional
  public HeroResource create(HeroResource resource) {
    HeroEntity entity = map(resource);
    entity = save(entity);
    return resourceAssembler.toResource(entity);
  }

  @Override
  @Transactional
  public Optional<HeroResource> update(String reference, HeroResource resource) {
    return updateEntity(reference, resource)
        .map(resourceAssembler::toResource);
  }
}
