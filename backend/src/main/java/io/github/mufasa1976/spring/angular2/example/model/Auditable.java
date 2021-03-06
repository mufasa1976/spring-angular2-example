package io.github.mufasa1976.spring.angular2.example.model;

import java.time.LocalDateTime;

public interface Auditable {
  String getCreatedBy();
  LocalDateTime getCreatedAt();
  String getLastModifiedBy();
  LocalDateTime getLastModifiedAt();
}
