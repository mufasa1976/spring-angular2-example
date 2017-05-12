package io.github.mufasa1976.spring.oauth2.example.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "HELLO_WORLD")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HelloWorldEntity extends AbstractPersistableEntity {
  private String value;
}
