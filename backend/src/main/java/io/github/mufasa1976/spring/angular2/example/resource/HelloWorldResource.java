package io.github.mufasa1976.spring.angular2.example.resource;

import java.time.LocalDateTime;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Relation(value = "helloWorld", collectionRelation = "helloWorlds")
public class HelloWorldResource extends AbstractResource {

  private String value;

  @Builder
  @JsonCreator
  public HelloWorldResource(
      @JsonProperty("reference") final String reference,
      @JsonProperty("version") final int version,
      @JsonProperty("createdBy") final String createdBy,
      @JsonProperty("createdAt") final LocalDateTime createdAt,
      @JsonProperty("lastModifiedBy") final String lastModifiedBy,
      @JsonProperty("lastModifiedAt") final LocalDateTime lastModifiedAt,
      @JsonProperty("value") final String value) {
    super(reference, version, createdBy, createdAt, lastModifiedBy, lastModifiedAt);
    this.value = value;
  }

}
