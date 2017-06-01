package io.github.mufasa1976.spring.angular2.example.model;

import java.io.Serializable;

public interface ReferableEntity<R extends Serializable> {
  R getReference();
}
