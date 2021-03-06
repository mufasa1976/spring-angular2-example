package io.github.mufasa1976.spring.angular2.example.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDateTime;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Relation(value = "hero", collectionRelation = "heroes")
public class HeroResource extends AbstractResource {

  private String name;

  @Builder
  @JsonCreator
  public HeroResource(
      @JsonProperty("reference") final String reference,
      @JsonProperty("version") final int version,
      @JsonProperty("createdBy") final String createdBy,
      @JsonProperty("createdAt") final LocalDateTime createdAt,
      @JsonProperty("lastModifiedBy") final String lastModifiedBy,
      @JsonProperty("lastModifiedAt") final LocalDateTime lastModifiedAt,
      @JsonProperty("name") final String name) {
    super(reference, version, createdBy, createdAt, lastModifiedBy, lastModifiedAt);
    this.name = name;
  }
}
