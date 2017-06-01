package io.github.mufasa1976.spring.angular2.example.service.impl;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;

import com.querydsl.core.types.Predicate;
import org.modelmapper.ModelMapper;

import io.github.mufasa1976.spring.angular2.example.model.AbstractPersistableEntity;
import io.github.mufasa1976.spring.angular2.example.repository.BaseRepository;
import io.github.mufasa1976.spring.angular2.example.resource.AbstractResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

abstract class AbstractServiceImpl<ENTITY extends AbstractPersistableEntity, RESOURCE extends AbstractResource, REPOSITORY extends BaseRepository<ENTITY>> {

  private final ModelMapper modelMapper;
  private final Class<ENTITY> entityClass;
  private final REPOSITORY repository;
  private final EntityManager entityManager;

  AbstractServiceImpl(
      final ModelMapper modelMapper,
      final Class<ENTITY> entityClass,
      final REPOSITORY repository,
      final EntityManager entityManager) {
    this.modelMapper = modelMapper;
    this.entityClass = entityClass;
    this.repository = repository;
    this.entityManager = entityManager;
  }

  Page<ENTITY> findAll(Pageable pageable, Predicate predicate) {
    return repository.findAll(predicate, pageable);
  }

  Page<ENTITY> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  Optional<ENTITY> findByReference(String reference) {
    return repository.findByReference(reference);
  }

  ENTITY map(RESOURCE resource) {
    return modelMapper.map(resource, entityClass);
  }

  ENTITY save(ENTITY entity) {
    return repository.save(entity);
  }

  Optional<ENTITY> updateEntity(String reference, RESOURCE resource) {
    return repository.findByReference(reference)
        .map(entity -> updateEntity(entity, resource));
  }

  private ENTITY updateEntity(ENTITY entity, RESOURCE resource) {
    entityManager.detach(entity);
    modelMapper.map(resource, entity);
    entity = repository.saveAndFlush(entity);
    entityManager.refresh(entity);
    return entity;
  }

  @Transactional
  public void delete(String reference) {
    repository.findByReference(reference)
        .ifPresent(repository::delete);
  }
}
