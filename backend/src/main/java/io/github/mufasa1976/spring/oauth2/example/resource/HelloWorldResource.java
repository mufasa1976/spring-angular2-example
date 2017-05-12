package io.github.mufasa1976.spring.oauth2.example.resource;

import java.time.LocalDateTime;

import org.springframework.hateoas.core.Relation;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Relation(value = "helloWorld", collectionRelation = "helloWorlds")
public class HelloWorldResource extends AbstractResource {

  private String value;

  @Builder
  public HelloWorldResource(
      final int version,
      final String createdBy,
      final LocalDateTime createdAt,
      final String lastModifiedBy,
      final LocalDateTime lastModifiedAt,
      final String value) {
    super(version, createdBy, createdAt, lastModifiedBy, lastModifiedAt);
    this.value = value;
  }

}
