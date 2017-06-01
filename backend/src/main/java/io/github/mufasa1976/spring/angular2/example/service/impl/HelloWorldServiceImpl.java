package io.github.mufasa1976.spring.angular2.example.service.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import io.github.mufasa1976.spring.angular2.example.assembler.HelloWorldResourceAssembler;
import io.github.mufasa1976.spring.angular2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.angular2.example.model.QHelloWorldEntity;
import io.github.mufasa1976.spring.angular2.example.resource.HelloWorldResource;
import io.github.mufasa1976.spring.angular2.example.service.HelloWorldService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import io.github.mufasa1976.spring.angular2.example.repository.HelloWorldRepository;
import lombok.RequiredArgsConstructor;

@Service
class HelloWorldServiceImpl extends AbstractServiceImpl<HelloWorldEntity, HelloWorldResource, HelloWorldRepository> implements HelloWorldService {

  private final PagedResourcesAssembler<HelloWorldEntity> pagedResourcesAssembler;
  private final HelloWorldResourceAssembler resourceAssembler;

  HelloWorldServiceImpl(
      final ModelMapper modelMapper,
      final HelloWorldRepository repository,
      final EntityManager entityManager,
      final PagedResourcesAssembler<HelloWorldEntity> pagedResourcesAssembler,
      final HelloWorldResourceAssembler resourceAssembler) {
    super(modelMapper, HelloWorldEntity.class, repository, entityManager);
    this.pagedResourcesAssembler = pagedResourcesAssembler;
    this.resourceAssembler = resourceAssembler;
  }

  @Override
  public PagedResources<HelloWorldResource> readAll(Pageable pageable, Optional<String> filter) {
    return pagedResourcesAssembler.toResource(findAll(pageable, buildQuery(filter)), resourceAssembler);
  }

  private Predicate buildQuery(Optional<String> filter) {
    BooleanBuilder builder = new BooleanBuilder();
    QHelloWorldEntity helloWorld = QHelloWorldEntity.helloWorldEntity;
    filter.ifPresent(v -> builder.and(helloWorld.value.containsIgnoreCase(v)));
    return builder.getValue();
  }

  @Override
  public Optional<HelloWorldResource> read(String reference) {
    return findByReference(reference)
        .map(resourceAssembler::toResource);
  }

  @Override
  @Transactional
  public HelloWorldResource create(HelloWorldResource resource) {
    HelloWorldEntity entity = map(resource);
    entity = save(entity);
    return resourceAssembler.toResource(entity);
  }

  @Override
  @Transactional
  public Optional<HelloWorldResource> update(String reference, HelloWorldResource resource) {
    return updateEntity(reference, resource)
        .map(resourceAssembler::toResource);
  }
}
