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
@RequiredArgsConstructor
public class HelloWorldServiceImpl implements HelloWorldService {

  private final ModelMapper modelMapper;
  private final PagedResourcesAssembler<HelloWorldEntity> pagedResourcesAssembler;
  private final HelloWorldResourceAssembler resourceAssembler;
  private final HelloWorldRepository repository;
  private final EntityManager entityManager;

  @Override
  public PagedResources<HelloWorldResource> readAll(Pageable pageable, Optional<String> filter) {
    Page<HelloWorldEntity> page = repository.findAll(buildQuery(filter), pageable);
    return pagedResourcesAssembler.toResource(page, resourceAssembler);
  }

  private Predicate buildQuery(Optional<String> filter) {
    BooleanBuilder builder = new BooleanBuilder();
    QHelloWorldEntity helloWorld = QHelloWorldEntity.helloWorldEntity;
    filter.ifPresent(v -> builder.and(helloWorld.value.containsIgnoreCase(v)));
    return builder.getValue();
  }

  @Override
  public Optional<HelloWorldResource> read(Long id) {
    return Optional.ofNullable(repository.findOne(id))
        .map(resourceAssembler::toResource);
  }

  @Override
  @Transactional
  public HelloWorldResource create(HelloWorldResource resource) {
    HelloWorldEntity entity = modelMapper.map(resource, HelloWorldEntity.class);
    entity = repository.save(entity);
    return resourceAssembler.toResource(entity);
  }

  @Override
  @Transactional
  public Optional<HelloWorldResource> update(Long id, HelloWorldResource resource) {
    return Optional.ofNullable(repository.findOne(id))
        .map(entity -> update(entity, resource))
        .map(resourceAssembler::toResource);
  }

  private HelloWorldEntity update(HelloWorldEntity entity, HelloWorldResource resource) {
    entityManager.detach(entity);
    modelMapper.map(resource, entity);
    entity = repository.saveAndFlush(entity);
    entityManager.refresh(entity);
    return entity;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    repository.delete(id);
  }
}
