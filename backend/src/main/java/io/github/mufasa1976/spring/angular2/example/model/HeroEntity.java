package io.github.mufasa1976.spring.angular2.example.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "HEROES")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HeroEntity extends AbstractPersistableEntity {
  @NotNull
  private String name;
}
