package io.github.mufasa1976.spring.oauth2.example.resource;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
abstract class AbstractResource extends ResourceSupport {

  private int version;

  private String createdBy;
  private LocalDateTime createdAt;
  private String lastModifiedBy;
  private LocalDateTime lastModifiedAt;

}
