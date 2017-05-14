package io.github.mufasa1976.spring.oauth2.example.service.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import io.github.mufasa1976.spring.oauth2.example.assembler.HelloWorldResourceAssembler;
import io.github.mufasa1976.spring.oauth2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.oauth2.example.model.QHelloWorldEntity;
import io.github.mufasa1976.spring.oauth2.example.repository.HelloWorldRepository;
import io.github.mufasa1976.spring.oauth2.example.resource.HelloWorldResource;
import io.github.mufasa1976.spring.oauth2.example.service.HelloWorldService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelloWorldServiceImpl implements HelloWorldService {

  private final ModelMapper modelMapper;
  private final PagedResourcesAssembler<HelloWorldEntity> pagedResourcesAssembler;
  private final HelloWorldResourceAssembler helloWorldResourceAssembler;
  private final HelloWorldRepository helloWorldRepository;
  private final EntityManager entityManager;

  @Override
  public PagedResources<HelloWorldResource> readAll(Pageable pageable, Optional<String> filter) {
    return pagedResourcesAssembler.toResource(helloWorldRepository.findAll(buildQuery(filter), pageable), helloWorldResourceAssembler);
  }

  private Predicate buildQuery(Optional<String> filter) {
    BooleanBuilder builder = new BooleanBuilder();
    QHelloWorldEntity helloWorld = QHelloWorldEntity.helloWorldEntity;
    filter.ifPresent(v -> builder.and(helloWorld.value.containsIgnoreCase(v)));
    return builder.getValue();
  }

  @Override
  public Optional<HelloWorldResource> read(Long id) {
    return Optional.ofNullable(helloWorldRepository.findOne(id))
        .map(helloWorldResourceAssembler::toResource);
  }

  @Override
  @Transactional
  public HelloWorldResource create(HelloWorldResource resource) {
    HelloWorldEntity entity = modelMapper.map(resource, HelloWorldEntity.class);
    entity = helloWorldRepository.save(entity);
    return helloWorldResourceAssembler.toResource(entity);
  }

  @Override
  @Transactional
  public Optional<HelloWorldResource> update(Long id, HelloWorldResource resource) {
    return Optional.ofNullable(helloWorldRepository.findOne(id))
        .map(entity -> update(entity, resource))
        .map(helloWorldResourceAssembler::toResource);
  }

  private HelloWorldEntity update(HelloWorldEntity entity, HelloWorldResource resource) {
    entityManager.detach(entity);
    modelMapper.map(resource, entity);
    entity = helloWorldRepository.saveAndFlush(entity);
    entityManager.refresh(entity);
    return entity;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    helloWorldRepository.delete(id);
  }
}
