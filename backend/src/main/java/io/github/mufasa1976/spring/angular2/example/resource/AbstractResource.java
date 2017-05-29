package io.github.mufasa1976.spring.angular2.example.resource;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString
abstract class AbstractResource extends ResourceSupport {

  private int version;

  private String createdBy;
  private LocalDateTime createdAt;
  private String lastModifiedBy;
  private LocalDateTime lastModifiedAt;

}