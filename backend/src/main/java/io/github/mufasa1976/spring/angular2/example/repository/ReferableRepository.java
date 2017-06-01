package io.github.mufasa1976.spring.angular2.example.repository;

import io.github.mufasa1976.spring.angular2.example.model.ReferableEntity;

import java.io.Serializable;
import java.util.Optional;

public interface ReferableRepository<T extends ReferableEntity, R extends Serializable> {
  Optional<T> findByReference(R reference);
}
